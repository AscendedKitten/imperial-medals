/* CREATE TYPE medal AS ENUM ('VONAMOR_CROSS','MEDAL_OF_CONQUEST','MEDAL_OF_SCALES','MEDAL_OF_CAMARADERIE','MEDAL_OF_SAINT_LILIUM','MEDAL_OF_OBURO','MEDAL_OF_IMPERIAL_SERVICE','MEDAL_OF_CHARITY','MEDAL_OF_RECREATION');
Postgres is fucking stupid and will crash if you add back that line beyond first run
 */

DROP TABLE IF EXISTS member;

CREATE TABLE IF NOT EXISTS member (
    id SERIAL PRIMARY KEY,
    user_name VARCHAR(16),
    medals text[]
);