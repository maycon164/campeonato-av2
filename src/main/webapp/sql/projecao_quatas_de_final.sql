USE campeonato;


/*VIEW DE PROJEÇÃO DAS QUARTAS DE FINAL*/
/* é recomendado executar a procedure resultado_geral para ter a certeza de que a view esta atualizada*/

DROP VIEW vw_quartas_de_final;

CREATE VIEW vw_quartas_de_final 
AS

	WITH classificados  (rownum, id_time, nome, sigla)
	AS (
		SELECT TOP 8 cg.rownum, cg.id_time, vtg.nome, vtg.sigla 
		FROM classificar_grupos() cg, vw_time_grupo vtg 
		WHERE cg.id_time = vtg.codigoTime
		ORDER BY cg.rownum
	)
	
	SELECT 	c1.id_time AS idTimeCasa, c1.nome as timeCasa, c1.sigla AS grupoCasa, 0 as golsCasa,
	 		c2.id_time as idTimeFora, c2.nome as timeFora, c2.sigla as grupoFora, 0 as golsFora,
	 		null as data
	FROM classificados c1, classificados c2
	WHERE c1.rownum = c2.rownum - 1
	AND c1.rownum % 2 != 0
	
SELECT * FROM vw_quartas_de_final;


