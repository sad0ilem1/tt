# Simulador de Escalonamento de CPU - TP1 (Sistemas Operacionais)

Este projeto consiste na implementação de um simulador de escalonamento para a disciplina de Sistemas Operacionais. O objetivo principal é comparar como diferentes políticas de gerenciamento de processos impactam a eficiência do sistema, analisando métricas como tempo de espera, turnaround e vazão.

## 🚀 Como Compilar e Executar

O simulador foi desenvolvido em **Java**. Certifique-se de que o seu ambiente (JDK) está configurado corretamente.

1. **Compilação:**
   No terminal, dentro da pasta do projeto, compile os arquivos `.java`:
   
   `javac App.java`

2. **Execução:**
   Para rodar o simulador, chame a classe principal passando o arquivo de processos como argumento:
   
   `java App processos.txt`
   
   *Nota: O arquivo `processos.txt` deve estar presente no diretório raiz para que o simulador consiga ler os dados de entrada.*

---

## 📊 Análise dos Algoritmos e Resultados

Abaixo, apresento uma análise de como cada política de escalonamento se comportou durante os testes realizados.

### 1. FCFS (First-Come, First-Served)
Este é o modelo mais simples de escalonamento não-preemptivo: quem chega primeiro, assume o controle da CPU. 
* **Observação:** O algoritmo é previsível, mas sofre com o "efeito comboio" se um processo longo chegar primeiro. No teste, ele manteve uma vazão de **0.50 processos/10 u.t.**, mas o tempo de espera médio (**14.00**) foi consideravelmente maior que o do SRTF.

### 2. SRTF (Shortest Remaining Time First)
Uma variante preemptiva do SJF que foca no processo que terminará mais rápido.
* **Observação:** Foi o grande vencedor em termos de responsividade. Ele conseguiu zerar o tempo de espera de processos menores (103 e 104) ao interromper tarefas mais longas. É o algoritmo mais eficiente para reduzir a média de espera (**11.67**), embora a preempção constante possa reduzir levemente a vazão total do sistema (**0.43**).

### 3. Round-Robin com Quantum por Predição
Aqui, o diferencial é o escalonamento circular com ajuste dinâmico do quantum usando a **Média Exponencial** (α = 0.5). O sistema prevê a duração do próximo surto de CPU através da fórmula: `tau_n+1 = alpha * t_n + (1 - alpha) * tau_n`.
* **Observação:** Apresentou as métricas de tempo mais elevadas. Isso acontece porque a alternância constante e o ajuste do quantum fazem com que os processos "andem juntos" na fila de prontos, demorando mais para que qualquer um deles seja totalmente concluído individualmente nesta massa de dados. O tempo de espera médio foi de **19.67**.

### 4. Multilevel Queue (MLQ)
* **Status:** Pendente.
* **Lógica:** Implementação de duas filas estáticas. A Fila 1 (Alta Prioridade) utiliza Round-Robin (quantum fixo), e a Fila 2 (Baixa Prioridade) utiliza FCFS. Processos na Fila 2 só executam se a Fila 1 estiver vazia.

---

## 📈 Comparativo de Métricas

| Algoritmo | Espera Média | Turnaround Médio | Vazão (Proc / 10 u.t.) |
| :--- | :---: | :---: | :---: |
| **FCFS** | 14.00 | 35.67 | 0.50 |
| **SRTF** | 11.67 | 33.33 | 0.43 |
| **Round-Robin** | 19.67 | 41.33 | 0.50 |
| **MLQ** | *Pendente* | *Pendente* | *Pendente* |

---

## 🛠️ Tecnologias Utilizadas
* **Linguagem:** Java
* **Conceitos Aplicados:** FCFS, SRTF, Round-Robin, MLQ, Previsão por Média Exponencial.
