DO
$do$
BEGIN
   IF NOT EXISTS (SELECT FROM pg_catalog.pg_roles WHERE rolname = 'devuser') THEN
      CREATE ROLE devuser WITH LOGIN PASSWORD 'devpass';
   END IF;
END
$do$;

DO
$do$
BEGIN
   IF NOT EXISTS (SELECT FROM pg_database WHERE datname = 'flair_dev_db') THEN
      CREATE DATABASE flair_dev_db OWNER devuser;
   END IF;
END
$do$;

GRANT ALL PRIVILEGES ON DATABASE flair_dev_db TO devuser;
