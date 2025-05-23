-- data.sql 예시
-- data.sql 예시

-- BOARDS 테이블에 더미 데이터 삽입 (BoardDTO 필드에 맞게)
INSERT INTO BOARDS (ID, NAME, CONTENTS)
VALUES (1, '첫 번째 게시글222', '안녕하세요, 첫 번째 게시글 내용입니다.');

INSERT INTO BOARDS (NAME, CONTENTS, CREATED_AT, UPDATED_AT)
VALUES (2, '두 번째 게시글', '두 번째 게시글의 내용입니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO BOARDS (NAME, CONTENTS, CREATED_AT, UPDATED_AT)
VALUES (3, '세 번째 게시글', '세 번째 게시글, 더 자세한 설명을 포함한 내용입니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO BOARDS (NAME, CONTENTS, CREATED_AT, UPDATED_AT)
VALUES (4, '네 번째 게시글', '세 번째 게시글, 더 자세한 설명을 포함한 내용입니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);