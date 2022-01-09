CREATE OR REPLACE FUNCTION get_schedule(trip_id bigint)
RETURNS TABLE(
	name text,
	arrivaltime timestamp,
	departuretime timestamp
) AS
$$
DECLARE
	station json;
	route json;
	departure_t timestamp;
BEGIN
	SELECT stations FROM t_route WHERE id IN (
		SELECT route_id
		FROM t_trip
		WHERE id = trip_id
	) INTO route;
	SELECT t_trip.departure_time FROM t_trip WHERE id = trip_id INTO departure_t;
	FOR station IN SELECT * FROM json_array_elements(route)
	LOOP
		RETURN QUERY SELECT
		station ->> 'stationName',
		departure_t
		+ make_interval(
			hours => (station -> 'arrivalPeriod' ->> 'hours')::int,
			mins => (station -> 'arrivalPeriod' ->> 'minutes'):: int
		),
		departure_t
		+ make_interval(
			hours => ((station -> 'arrivalPeriod' ->> 'hours'):: int + (station -> 'stop' ->> 'hours')::int),
			mins => ((station -> 'arrivalPeriod' ->> 'minutes'):: int + (station -> 'stop' ->> 'minutes')::int)
		);
	END LOOP;
END
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION create_periodic_trip(
	trip_varchar varchar
)
RETURNS TABLE(
	id int,
	departure_time timestamp,
	route_id bigint,
	train_id int,
	trip_status varchar
) AS
$$
DECLARE
	current_departure timestamp;
	trip json;
BEGIN
	trip = trip_varchar::json;
	current_departure = (trip ->> 'firstTripDate')::timestamp;
	WHILE current_departure <= (trip ->> 'finishDate')::timestamp
	LOOP
		RETURN QUERY INSERT INTO t_trip(
			departure_time,
			route_id,
			train_id,
			trip_status
		) VALUES(
			current_departure,
			(trip ->> 'routeId')::bigint,
			(trip ->> 'trainId')::int,
			'SCHEDULED'
		) RETURNING *;
		current_departure = current_departure + make_interval(
			hours => (trip -> 'interval' ->> 'hours')::int,
			mins => (trip-> 'interval' ->> 'minutes')::int
		);
	END LOOP;
END
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_popular_routes(month int, YEAR int, amount int)
RETURNS TABLE(
	routeid bigint,
	ticketssold int
) AS
$$
	SELECT
		trip.route_id,
		count(ticket.id) tickets_sold
	FROM t_trip trip
	JOIN t_ticket ticket ON trip.id = ticket.trip_id
	WHERE
		date_part('month', trip.departure_time) = $1
		AND
		date_part('year', trip.departure_time) = $2
	GROUP BY trip.route_id
	ORDER BY tickets_sold DESC
	LIMIT $3
$$ LANGUAGE SQL;
