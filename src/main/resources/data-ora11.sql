-- 1. 사용자 생성 및 권한 부여 (SYSTEM 계정에서 실행)
CREATE USER oracle IDENTIFIED BY oracle;
GRANT CONNECT, RESOURCE TO oracle;
ALTER USER oracle ACCOUNT UNLOCK;

------------------------------------------------------
-- 2. 오라클 스키마(oracle)로 변경 (선택사항)
-- ALTER SESSION SET CURRENT_SCHEMA = oracle;
------------------------------------------------------

-- 기존 객체 삭제 (존재하는 경우)
BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE oracle.boards CASCADE CONSTRAINTS';
EXCEPTION
  WHEN OTHERS THEN
    IF SQLCODE != -942 THEN  -- 테이블이 없는 경우 무시
      RAISE;
    END IF;
END;
/

BEGIN
  EXECUTE IMMEDIATE 'DROP SEQUENCE oracle.boards_seq';
EXCEPTION
  WHEN OTHERS THEN
    IF SQLCODE != -2289 THEN  -- 시퀀스가 없는 경우 무시
      RAISE;
    END IF;
END;
/

-- 테이블 생성
CREATE TABLE oracle.boards (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(255) NOT NULL,
    contents CLOB NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 시퀀스 생성
CREATE SEQUENCE oracle.boards_seq
  START WITH 1
  INCREMENT BY 1
  NOCACHE
  NOCYCLE;

-- 트리거 생성
CREATE OR REPLACE TRIGGER oracle.boards_bir
BEFORE INSERT ON oracle.boards
FOR EACH ROW
WHEN (NEW.id IS NULL)
BEGIN
    SELECT oracle.boards_seq.NEXTVAL INTO :NEW.id FROM dual;
END;
/

-- DML: 데이터 삽입
INSERT INTO oracle.boards (id, name, contents, created_at, updated_at)
VALUES (boards_seq.NEXTVAL, 'Board 1', 'This is the dummy content for board 1.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO oracle.boards (id, name, contents, created_at, updated_at)
VALUES (boards_seq.NEXTVAL, 'Board 2', 'This is the dummy content for board 2.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO oracle.boards (id, name, contents, created_at, updated_at)
VALUES (boards_seq.NEXTVAL, 'Board 3', 'This is the dummy content for board 3.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO oracle.boards (id, name, contents, created_at, updated_at)
VALUES (boards_seq.NEXTVAL, 'Board 4', 'This is the dummy content for board 4.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO oracle.boards (id, name, contents, created_at, updated_at)
VALUES (boards_seq.NEXTVAL, 'Board 5', 'This is the dummy content for board 5.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO oracle.boards (id, name, contents, created_at, updated_at)
VALUES (boards_seq.NEXTVAL, 'Board 6', 'This is the dummy content for board 6.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO oracle.boards (id, name, contents, created_at, updated_at)
VALUES (boards_seq.NEXTVAL, 'Board 7', 'This is the dummy content for board 7.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO oracle.boards (id, name, contents, created_at, updated_at)
VALUES (boards_seq.NEXTVAL, 'Board 8', 'This is the dummy content for board 8.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

COMMIT;