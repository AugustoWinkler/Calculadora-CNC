# Calculadora CNC

## Visão Geral

O projeto **Calculadora CNC** foi idealizado para resolver um problema comum ao tentar calcular o valor da hora máquina de uma máquina laser CNC. Durante a tentativa de passar um orçamento, percebi que o cálculo não era claro e, além disso, era trabalhoso calcular para várias máquinas. A maioria das soluções encontradas eram planilhas pagas, então decidi criar uma ferramenta própria. Assim surgiu o projeto **Calculadora CNC**.

## Tecnologias Utilizadas

- **Linguagem de Programação**: Java
- **Interface Gráfica**: JavaFX (com o auxílio do SceneBuilder)
- **Padrão de Arquitetura**: MVC (Model-View-Controller)
- **Padrão de Design**: Singleton para as classes DAO (Data Access Object)
- **Persistência de Dados**: SQLite
- **Gestão de Dependências**: Gradle
- **Gerenciamento de Build**: Plugin Shadow (para gerar um Fat/Uber Jar com dependências)
- **Criação de Executável**: Launch4j

## Requisitos

Para executar o projeto, é necessário ter o **Java SE 9.0 ou superior** instalado no sistema.

## Download do Executável

Você pode baixar o executável clicando no link abaixo:

[Download Executável - Version 1](https://github.com/AugustoWinkler/LaserCalc/releases/download/Version-1/Calculadora.exe)

## Descrição da Interface

A interface do usuário foi projetada para ser simples e intuitiva. Na tela principal, podemos observar os seguintes componentes:

![Tela principal](https://github.com/AugustoWinkler/LaserCalc/blob/main/Img/Tela%20Principal.png)



### Parte Superior
- **Campos de Máquina, Material e Operacional**:
  - **Máquina**: Permite criar uma nova máquina, remover ou alterar uma máquina existente.
  - **ComboBox**: Para selecionar a máquina desejada para o cálculo.
  - O mesmo se aplica aos campos **Material** e **Operacional**.

### Parte Inferior Esquerda
- **Campos**: 
  - **Horas**: Define a quantidade de horas que o corte levará.
  - **Altura** e **Largura**: Definem as dimensões do corte em milímetros e são usadas para calcular a área.
  - **Lucro Desejado**: Percentual de lucro que o usuário deseja adicionar ao cálculo.
  - **Botão Calcular**: Responsável por realizar o cálculo com os dados inseridos. Ele só funciona se uma máquina e um operacional estiverem selecionados. Os resultados são atualizados e exibidos na interface.

## Como Adicionar uma Máquina


![Tela Maquina](https://github.com/AugustoWinkler/LaserCalc/blob/main/Img/TelaMaquina.png)

A janela de **Adicionar Máquina** permite preencher as seguintes informações:

- **Nome**: Nome da máquina.
- **Valor Máquina**: Custo inicial da máquina.
- **Vida Útil**: Prazo de operação da máquina em anos (normalmente 10 anos).
- **Valor Residual**: Valor da máquina após o fim de sua vida útil (geralmente 0).
- **Valor Laser**: Custo do cabeçote de laser.
- **Vida Útil Laser**: Quantidade de horas que o laser dura, fornecida pelo fabricante.

Após preencher os campos, basta clicar em **Salvar**, e a máquina será registrada e poderá ser selecionada na interface principal.

## Como Adicionar um Material

![Tela Material](https://github.com/AugustoWinkler/LaserCalc/blob/main/Img/TelaMaterial.png)

A janela de **Adicionar Material** permite preencher as seguintes informações:

- **Nome**: Nome do material (exemplo: MDF3mm, MDF6mm).
- **Preço**: Valor do metro quadrado do material.

Após preencher os campos, basta clicar em **Salvar**, e o material será registrado e poderá ser selecionado na interface principal.

## Como Adicionar um Operacional

![Tela Operacional](https://github.com/AugustoWinkler/LaserCalc/blob/main/Img/TelaOperacional.png)

A janela de **Adicionar Operacional** permite preencher as seguintes informações:

- **Nome**: Nome do operacional (exemplo: 12x24).
- **Dias**: Dias de operação por mês.
- **Horas**: Horas trabalhadas por dia.
- **Despesas**: Custos adicionais de operação (energia, manutenção, aluguel, etc.).
- **Custo Operador**: Salário do operador da máquina.

Após preencher os campos, basta clicar em **Salvar**, e o operacional será registrado e poderá ser selecionado na interface principal.

## Explicação do Cálculo

O cálculo do custo total leva em consideração os seguintes parâmetros:

- **Valor da Máquina** (VM)
- **Valor Residual** (VR)
- **Vida Útil da Máquina** (VU)
- **Valor do Laser** (VL)
- **Vida Útil do Laser** (VUL)
- **Horas de Trabalho por Dia** (HTD)
- **Dias de Trabalho por Mês** (DTD)
- **Salário do Operador** (SO)
- **Despesas Operacionais** (DO)

A fórmula para o cálculo do custo total é:

### Cálculo Total

$$
\text{Custo Total} = \left( \frac{\text{Depreciação da Máquina}}{12} \right) \div \left( \text{Horas de Trabalho} \right) + \text{Depreciação do Laser} + \frac{\text{Salário do Operador} + \text{Despesas Operacionais}}{\text{Horas de Trabalho}}
$$

Onde:

$$
\text{Depreciação da Máquina} = \frac{\text{VM} - \text{VR}}{\text{VU}}
$$

$$
\text{Depreciação do Laser} = \frac{\text{VL}}{\text{VUL}}
$$

$$
\text{Horas de Trabalho} = \text{HTD} \times \text{DTD}
$$

$$
\text{Custo do Operador e Despesas} = \text{SO} + \text{DO}
$$

### Cálculo do Custo de Material

$$
\text{Custo do Material} = \left( \frac{\text{Altura}}{1000} \times \frac{\text{Largura}}{1000} \right) \times \text{Preço do Material}
$$

Onde:

- **Altura** e **Largura** devem ser preenchidos em milímetros e são convertidos para metros ao dividir por 1000.

## Conclusão

O projeto **Calculadora CNC** foi desenvolvido com o objetivo de facilitar o cálculo do valor da hora máquina para máquinas CNC, tornando o processo mais simples, claro e acessível. Através de uma interface intuitiva, este projeto oferece uma solução prática pessoas que lidam com corte a laser CNC.
