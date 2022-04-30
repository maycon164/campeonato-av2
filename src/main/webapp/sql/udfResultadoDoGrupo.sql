USE campeonato;

Fazer  uma  tela  de  consulta  com  os  4  grupos  e  4 Tabelas,  
que  mostrem  a  saída  (para  cadaTabela) de uma UDF (User Defined FUNCTION), 
que receba o nome do grupo, valide-o e dê aseguinte saída: 
GRUPO  (nome_time,  num_jogos_disputados*,  vitorias,  empates,  derrotas,  gols_marcados, gols_sofridos, saldo_gols**,pontos***) 
O campeão de cada grupo se dará por aquele que tiver maior número de pontos. Em caso de empate, a ordem de desempate é por número de vitórias, 
depois por gols marcados e por fim, por saldo de gols.
(Vitória = 3 pontos, Empate = 1 ponto , Derrota = 0 pontos)

CREATE FUNCTION resultado_grupo(
	@grupo VARCHAR(1)
)
RETURNS @tabela TABLE (
	nome_time				VARCHAR(100),
	id_time					INT,
	jogos_disputados		INT,
	vitorias				INT,
	empates					INT, 
	derrotas				INT,
	gols_marcados			INT,
	gols_sofridos			INT,
	saldo_gols				INT,
	pontos					INT
)
AS
BEGIN
	DECLARE @nome_time				VARCHAR(100),
			@jogos_disputados		INT,
			@vitorias				INT,
			@empates				INT, 
			@derrotas				INT,
			@gols_marcados			INT,
			@gols_sofridos			INT,
			@saldo_gols				INT,
			@pontos					INT;
		
	
	DECLARE @idTime INT;

	DECLARE c_percorre_grupo CURSOR 
		FOR SELECT codigoTime FROM grupos WHERE codigoGrupo = @grupo
	OPEN c_percorre_grupo
		BEGIN 
			
			FETCH NEXT FROM c_percorre_grupo into @idTime
			
			WHILE @@FETCH_STATUS = 0
				BEGIN
					
					SET @vitorias = (SELECT COUNT(*) FROM jogos WHERE timeCasa = @idTime AND golsCasa > golsFora);
					SET @vitorias = @vitorias + (SELECT COUNT(*) FROM jogos WHERE timeFora = @idTime AND golsFora > golsCasa);
					SET @empates = (SELECT count (*) FROM jogos WHERE golsCasa = golsFora AND (timeCasa = @idTime OR timeFora = @idTime));
					
					SET @derrotas = 12 - (@vitorias + @empates);
				
					SET @gols_marcados = ISNULL((SELECT sum(golsCasa) FROM jogos WHERE timeCasa = @idTime), 0);
					SET @gols_marcados = @gols_marcados + ISNULL((SELECT sum(golsFora) FROM jogos WHERE timeFora = @idTime), 0);

					SET @gols_sofridos = ISNULL((SELECT sum(golsFora) FROM jogos WHERE timeCasa = @idTime), 0);
					SET @gols_sofridos = @gols_sofridos + ISNULL((SELECT sum(golsCasa) FROM jogos WHERE timeFora = @idTime), 0);
				
					SET @saldo_gols = (@gols_marcados - @gols_sofridos);
				
	
					SET @pontos = (@vitorias * 3) + (@empates)
				 
					SET @nome_time = (SELECT nome from times where codigoTime = @idTime)
					
					INSERT INTO @tabela VALUES
					(@nome_time,@idTime, 12, @vitorias, @empates, @derrotas, @gols_marcados, @gols_sofridos, @saldo_gols, @pontos);
					FETCH NEXT FROM c_percorre_grupo into @idTime
				END
			
		END
	CLOSE c_percorre_grupo
	DEALLOCATE c_percorre_grupo
	RETURN
END




SELECT * FROM grupos WHERE codigoGrupo 
SELECT * from  resultado_grupo('C')

	