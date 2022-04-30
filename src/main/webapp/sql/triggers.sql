USE campeonato;

SELECT * FROM exemplo e;
SELECT * FROM grupos;
SELECT * FROM times;
SELECT * FROM jogos;


--trigger que impede insert, update, delete nas tabelas de times e jogos

CREATE TRIGGER tg_prevent_times
ON times
AFTER INSERT, UPDATE, DELETE 
AS
BEGIN
	PRINT 'CAN NOT PERFORM THIS OPERATION';
	ROLLBACK;
END

CREATE TRIGGER tg_prevent_grupos
ON grupos
AFTER INSERT, UPDATE, DELETE 
AS
BEGIN
	PRINT 'CAN NOT PERFORM THIS OPERATION';
	ROLLBACK;
END

--trigger para impedir o insert e delete na tabela jogos

CREATE TRIGGER tg_prevent_jogos
ON jogos
AFTER INSERT, DELETE 
AS
BEGIN
	PRINT 'CAN NOT PERFORM THIS OPERATION';
	ROLLBACK;
END

select * from campeonato

SELECT * from JOGOS where timeCasa = 11 or timeFora = 11

UPDATE jogos
SET golsFora = 20
WHERE timeFora  = 11

OR timeFora = 11


SELECT nome from times where codigoTime = 11
