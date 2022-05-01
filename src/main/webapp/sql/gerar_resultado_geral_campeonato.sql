USE campeonato;

DROP TABLE campeonato 

CREATE TABLE campeonato(
	nome_time				VARCHAR(100),
	id_time					INT,
	jogos_disputados		INT,
	vitorias				INT,
	empates					INT, 
	derrotas				INT,
	gols_marcados			INT,
	gols_sofridos			INT,
	saldo_gols				INT,
	pontos					INT,
	grupo 					VARCHAR(1),
	situacao				VARCHAR(MAX)
)

CREATE PROCEDURE resultado_geral
AS
BEGIN
	/*TODO: salvar o resultado da function resultado do grupo de cada grupo A - B - C - D*/
	DELETE campeonato;
	
	INSERT INTO campeonato(nome_time, id_time, jogos_disputados, vitorias, empates,
	derrotas, gols_marcados, gols_sofridos, saldo_gols, pontos, grupo)
	
	SELECT *, 'A' AS grupo FROM resultado_grupo('A')  
	UNION
	SELECT *, 'B' AS grupo FROM resultado_grupo('B')  
	UNION
	SELECT *, 'C' AS grupo FROM resultado_grupo('C')  
	UNION
	SELECT *, 'D' AS grupo FROM resultado_grupo('D')  


	/*TODO: update dos 8 classificados e os 2 rebaixados*/
	
	UPDATE campeonato
	SET situacao = 'CLASSIFICADO'
	WHERE id_time IN(SELECT id_time FROM classificar_grupos() WHERE rownum <= 8)

	UPDATE campeonato
	SET situacao = 'REBAIXADO'
	WHERE id_time IN(SELECT id_time FROM classificar_grupos() WHERE rownum > 8)
	
END

EXEC resultado_geral;

SELECT * FROM campeonato