USE campeonato;

DROP FUNCTION classificar_grupos

CREATE FUNCTION classificar_grupos()
RETURNS @tabela TABLE(
	rownum INT identity(1,1),
	id_time varchar(max)
)
AS
BEGIN
	/* os 8 primeiros são os classificados e os outros 2 são os rebaixados*/ 
	/*tabela com os ids do classificados*/
	
	INSERT INTO @tabela (id_time)
	SELECT TOP 2 id_time FROM campeonato 
	WHERE grupo = 'A'
	ORDER BY 	
	pontos DESC, 
	vitorias DESC, 
	gols_marcados DESC, 
	saldo_gols DESC
	
	INSERT INTO @tabela (id_time)
	SELECT TOP 2 id_time FROM campeonato 
	WHERE grupo = 'B'
	ORDER BY 	
	pontos DESC, 
	vitorias DESC, 
	gols_marcados DESC, 
	saldo_gols DESC
	
	INSERT INTO @tabela (id_time)
	SELECT TOP 2 id_time FROM campeonato 
	WHERE grupo = 'C'
	ORDER BY 	
	pontos DESC, 
	vitorias DESC, 
	gols_marcados DESC, 
	saldo_gols DESC
	
	
	INSERT INTO @tabela (id_time)
	SELECT TOP 2 id_time FROM campeonato 
	WHERE grupo = 'D'
	ORDER BY 	
	pontos DESC, 
	vitorias DESC, 
	gols_marcados DESC, 
	saldo_gols DESC
	


/*
 *  TODO: TIME COM MENOR PONTUAÇÃO DENTRE TODOS OS TIMES
 */
	INSERT INTO @tabela (id_time)
	SELECT TOP 2 id_time FROM campeonato ORDER BY pontos ASC
	
	RETURN 
END

SELECT * FROM classificar_grupos(); 
 