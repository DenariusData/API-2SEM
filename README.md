<img width=100% src="https://capsule-render.vercel.app/api?type=waving&color=FFA500&height=120&section=header"/>

[![Typing SVG](https://readme-typing-svg.herokuapp.com/?color=f4971f&size=50&center=true&vCenter=true&width=1000&lines=Olá,+somos+o+grupo+Denarius+Data!+:%29)](https://git.io/typing.svg)

# API-2sem

* [📘 Tema e Descrição do Produto](#tema-e-descrição-do-produto)
* [✅ Requisitos Funcionais](#requisitos-funcionais)
* [✅ Backlog do produto](#backolog-do-produto)
* [✅ User story](#user-story)
* [🎯 Missão](#missão)
* [🔭 Visão](#visão)
* [💡 Valores](#valores)
* [👨‍💻 Equipe](#equipe)
* [🛠 Tecnologias](#tecnologias)
* [📜 Requisitos de Permanência do Grupo](#requisitos-de-permanência-do-grupo)
* [📸 Nossa Equipe](#nossa-equipe) <!-- Link adicionado -->

## 📘 Tema e Descrição do Produto

Nosso grupo está desenvolvendo uma solução inovadora para aprimorar o processo de avaliação de competências no PACER, dentro da metodologia de Aprendizagem por Projetos Integrados.

Atualmente, a avaliação é realizada de forma manual, o que gera diversos desafios como:

- *Falta de padronização:* Cada grupo utiliza métodos diferentes para coletar e analisar os dados.
- *Margem para erros:* A possibilidade de erros humanos é alta, tanto na coleta quanto na análise dos dados.
- *Dificuldade em gerar relatórios:* A geração de relatórios personalizados é um processo demorado e complexo.

Nossa proposta é criar um sistema automatizado que irá:

- *Simplificar a avaliação:* Os alunos poderão avaliar seus colegas de forma rápida e intuitiva, diretamente no sistema.
- *Garantir a integridade dos dados:* O sistema irá armazenar todas as avaliações de forma segura e organizada.
- *Gerar relatórios personalizados:* Professores poderão gerar relatórios detalhados sobre o desempenho de cada aluno e grupo.
- *Flexibilizar o processo:* O sistema permitirá a inclusão de novos critérios de avaliação e a adaptação do calendário de Sprints.

Principais Funcionalidades:

- *Avaliação online:* Os alunos poderão realizar as avaliações de forma simples e rápida.
- *Geração de relatórios:* O sistema gerará relatórios personalizados para professores e alunos.
- *Gerenciamento de grupos:* Será possível criar, editar e excluir grupos, além de adicionar e remover membros.
- *Gerenciamento de critérios:* Os critérios de avaliação poderão ser personalizados e gerenciados pelos professores.
- *Calendário de Sprints:* O sistema irá gerenciar o calendário de Sprints, facilitando a associação das avaliações às Sprints corretas.
- *Controle de acesso:* O sistema utilizará autenticação por usuário e senha para garantir a segurança dos dados.

## ✅ Requisitos Funcionais

- Permitir que um aluno avalie todos os membros de sua equipe ao final de cada Sprint;
- Permitir que o professor consiga gerar um relatório contendo a nota média por aluno para cada critério de avaliação em uma determinada Sprint;
- Permitir que o professor consiga gerar um relatório contendo a nota média de todos os alunos de um grupo para uma determinada Sprint;
- Fornecer uma forma de carregar informações referentes aos grupos por meio de arquivo;
- Também deve ser possível incluir ou excluir membros em grupos no caso de realocações;
- Permitir o gerenciamento dos critérios de avaliação. Deve ser possível incluir novos critérios, além de alterar e desativar critérios existentes. Critérios desativados não devem aparecer durante uma avaliação;
- Permitir que o professor cadastre o calendário de Sprints para cada semestre. Em vez de solicitar o número da Sprint em uma avaliação, o sistema deve verificar a data atual e automaticamente assumir que ela está associada à Sprint que acabou de finalizar;
- Usar autenticação por usuário e senha para garantir que cada aluno possa realizar apenas uma avaliação por Sprint e que apenas o professor tenha acesso aos relatórios.
  
## ✅ Backolog do produto

[Backlog do produto](docs/backlog.pdf)
 

## ✅ User Story

| **Atividade**                           | **Epic**                                                                                                   | **Critério de Aceitação**                                                                                                                                                        |
|-----------------------------------------|------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Avaliação de Membros da Equipe         | Como aluno, Eu quero avaliar todos os membros da minha equipe ao final de cada Sprint, Para que o desempenho deles seja registrado e considerado na avaliação final. | O sistema deve permitir que cada aluno avalie todos os membros da equipe ao final de uma Sprint. O aluno deve poder fazer uma única avaliação por Sprint.                          |
| Relatório de Notas Individuais          | Como professor, Eu quero gerar um relatório contendo a nota média de cada aluno por critério de avaliação em uma determinada Sprint, Para que eu possa analisar o desempenho individual dos alunos. | O sistema deve gerar um relatório com a nota média por aluno em cada critério para uma Sprint específica.                                                                         |
| Relatório de Notas do Grupo             | Como professor, Eu quero gerar um relatório contendo a nota média de todos os alunos de um grupo para uma Sprint específica, Para que eu possa verificar o desempenho coletivo do grupo. | O sistema deve gerar um relatório com a média de todos os alunos de um grupo para uma Sprint.                                                                                    |
| Gerenciamento de Grupos                 | Como professor, Eu quero carregar informações de grupos por meio de um arquivo, Para que eu possa gerenciar os grupos de alunos de forma mais eficiente. | O sistema deve permitir a importação de grupos por arquivo e a inclusão/exclusão de membros de grupos.                                                                           |
| Gerenciamento de Critérios de Avaliação  | Como professor, Eu quero gerenciar os critérios de avaliação, Para que eu possa adicionar novos critérios, alterar ou desativar os existentes conforme necessário. | O sistema deve permitir a inclusão, alteração e desativação de critérios de avaliação. Critérios desativados não devem aparecer nas avaliações realizadas pelos alunos.            |
| Calendário de Sprints                   | Como professor, Eu quero cadastrar o calendário de Sprints para cada semestre, Para que o sistema identifique automaticamente a Sprint em que uma avaliação está sendo realizada. | O sistema deve verificar a data atual e associar automaticamente a avaliação à Sprint correspondente.                                                                             |
| Autenticação                            | Como aluno, Eu quero realizar login com usuário e senha, Para que eu possa garantir que apenas eu faça minha avaliação e que meus dados sejam protegidos. | O sistema deve exigir autenticação de usuário e senha para alunos realizarem a avaliação. Somente o professor deve ter acesso aos relatórios.                                      |

## Contribuições


## 🎯 Missão

Facilitar o acesso e a compreensão dos princípios da programação e lógica de algoritmos, capacitando indivíduos a desenvolverem habilidades essenciais para a resolução de problemas computacionais e contribuindo para a inovação e desenvolvimento tecnológico.

## 🔭 Visão

Nossa visão é nos tornarmos referência na área de desenvolvimento de sistemas, integrando de forma eficiente as funções dos colaboradores e compreendendo a importância da colaboração entre as diferentes áreas.

## 💡 Valores

Valorizamos a dedicação, o comprometimento com o desenvolvimento organizacional, a ética, a integridade e a transparência em todas as nossas ações.

<br>

## 👨‍💻 Equipe

#### *Integrantes:* 
- Rafael Slivka (Scrum Master)
- Larissa Claro (Product Owner)
- Thiago Abreu (Scrum Team)
- Mariana Yasmin (Scrum Team)
- Tiago Bernardo (Scrum Team)
- Beatriz Santos (Scrum Team)
- Luiz Ricardo (Scrum Team)

<br>

## 🛠 Tecnologias

#### Foram utilizadas as seguintes tecnologias:

<p align="left">
  <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white" height="30" alt="Java"/>
  <img src="https://img.shields.io/badge/Jira-%230A0FFF.svg?style=for-the-badge&logo=jira&logoColor=white" height="30" alt="Jira"/>
  <img src="https://img.shields.io/badge/Slack-%234A154B.svg?style=for-the-badge&logo=slack&logoColor=white" height="30" alt="Slack"/>
  <img src="https://img.shields.io/badge/Canva-%2300C4CC.svg?style=for-the-badge&logo=Canva&logoColor=white" height="30" alt="Canva"/>
</p>

## 📜 Requisitos de Permanência do Grupo

- Será aceito apenas 1 falta por mês nas reuniões semanais (quarta-feira).
- Respeitar os prazos e padrões de commit (organização).
- Expor dificuldades durante o processo, evitando problemas próximos à apresentação.
- Propõe-se que todos apresentem pelo menos uma sprint.

## 📸 Nossa Equipe

| *Rafael Slivka* (Scrum Master) | *Larissa Claro* (Product Owner) | *Thiago Abreu* (Scrum Team) | *Mariana Yasmin* (Scrum Team) |
|:-------------------------------:|:---------------------------------:|:----------------------------:|:------------------------------:|
| ![Rafael Slivka](/docs/assets-readme/rafael-slivka.jpg) | ![Larissa Claro](/docs/assets-readme/larissa-claro.jpg) | ![Thiago Abreu](/docs/assets-readme/thiago-abreu.jpg) | ![Mariana Yasmin](/docs/assets-readme/mariana-yasmin.jpg) |
| [<img src="https://upload.wikimedia.org/wikipedia/commons/c/ca/LinkedIn_logo_initials.png" width="20">](https://www.linkedin.com/in/rafael-lopes-slivka-07753326a/) | [<img src="https://upload.wikimedia.org/wikipedia/commons/c/ca/LinkedIn_logo_initials.png" width="20">](https://www.linkedin.com/in/clarolarissa/) | [<img src="https://upload.wikimedia.org/wikipedia/commons/c/ca/LinkedIn_logo_initials.png" width="20">](https://www.linkedin.com/in/thiagosabreu/) | [<img src="https://upload.wikimedia.org/wikipedia/commons/c/ca/LinkedIn_logo_initials.png" width="20">](https://www.linkedin.com/in/oliveirasmari/) |

| *Tiago Bernardo* (Scrum Team) | *Beatriz Sthefanny* (Scrum Team) | *Luiz Silva* (Scrum Team) |
|:-------------------------------:|:-------------------------------:|:----------------------------:|
| ![Tiago Bernardo](/docs/assets-readme/tiago-bernardo.jpg) | ![Beatriz Sthefanny](/docs/assets-readme/beatriz-santos.jpg) | ![Luiz Silva](/docs/assets-readme/luiz-ricardo.jpg) |
| [<img src="https://upload.wikimedia.org/wikipedia/commons/c/ca/LinkedIn_logo_initials.png" width="20">](https://www.linkedin.com/in/tiagobernardosantos/) | [<img src="https://upload.wikimedia.org/wikipedia/commons/c/ca/LinkedIn_logo_initials.png" width="20">](https://www.linkedin.com/in/beatriz-santos-0b6773220?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=ios_app) | [<img src="https://upload.wikimedia.org/wikipedia/commons/c/ca/LinkedIn_logo_initials.png" width="20">](https://www.linkedin.com/in/luiz-ricardo-mendes/) |
