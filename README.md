# Simulador de Escalonamento de CPU

Trabalho Prático 1 da disciplina de Sistemas Operacionais. O projeto é um simulador feito para comparar o desempenho de diferentes algoritmos de escalonamento de processos, avaliando o tempo de espera, tempo de retorno (turnaround) e vazão.

## ⚙️ Como executar

O projeto foi desenvolvido em **Java**. Para testar, basta compilar e rodar no terminal:

1. Compile o código:
   `javac App.java`

2. Execute passando o arquivo de processos:
   `java App processos.txt`

> **Aviso:** O arquivo `processos.txt` precisa estar na mesma pasta da classe principal para que a leitura dos dados funcione corretamente.

---

## 📊 Relatório de Desempenho

Baseado na execução do arquivo de teste fornecido, aqui estão as observações práticas sobre cada algoritmo:

### 1. FCFS (First-Come, First-Served)
Escalonamento não-preemptivo padrão, executando os processos estritamente por ordem de chegada.
- **Análise:** Foi o modelo mais simples, mas sofreu com o "efeito comboio" nos processos maiores. Manteve uma vazão constante de 0.50 processos/10 u.t., porém o tempo de espera médio (14.0) ficou bem acima do que vimos no SRTF.

### 2. SRTF (Shortest Remaining Time First)
Algoritmo preemptivo que sempre prioriza o processo com o menor tempo de CPU restante.
- **Análise:** Teve o melhor desempenho geral de tempo. Ele conseguiu zerar a espera dos processos mais rápidos (103 e 104) interrompendo a tarefa mais longa. Com isso, entregou a menor média de espera (11.67) e de turnaround (33.33), sofrendo apenas uma leve queda na vazão do sistema (0.43).

### 3. Round-Robin (Quantum por Predição)
Escalonamento preemptivo onde o quantum varia dinamicamente usando a **Média Exponencial** (α = 0.5) para prever o próximo surto de CPU. A fórmula base implementada foi: `tau_n+1 = alpha * t_n + (1 - alpha) * tau_n`.
- **Análise:** Acabou gerando os maiores tempos médios de espera (19.67) e turnaround (41.33). Como o quantum se ajusta o tempo todo, os processos ficam "competindo" mais pela CPU e acabam avançando juntos na fila. Isso faz com que demorem mais tempo total para finalizar individualmente no cenário que testamos.

### 4. Multilevel Queue (MLQ)
- *(Em desenvolvimento)*
- Vai implementar duas filas estáticas: a de alta prioridade rodando Round-Robin (com quantum fixo) e a de baixa prioridade rodando FCFS.

---

## 📈 Resultados Finais

| Algoritmo | Espera Média | Turnaround Médio | Vazão (Proc / 10 u.t.) |
| :--- | :---: | :---: | :---: |
| **FCFS** | 14.00 | 35.67 | 0.50 |
| **SRTF** | 11.67 | 33.33 | 0.43 |
| **Round-Robin** | 19.67 | 41.33 | 0.50 |
| **MLQ** | *-* | *-* | *-* |
