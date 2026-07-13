# Trabalho de Hotel - POO

Sistema de gerenciamento de reservas de hotel, feito para a disciplina de POO do 3º semestre (IFBA-SAJ).

## Sobre o projeto

O sistema serve pra cadastrar hóspedes e fazer reservas de quartos. Foi feito em Java com JavaFX pra interface gráfica, usando Maven pra gerenciar o projeto e Scene Builder pra desenhar as telas (arquivos .fxml).

## Como rodar

Precisa ter JDK 21+ e Maven instalados. Entra na pasta `trabalho-hotel` pelo terminal e roda:

```
mvn clean install
mvn javafx:run
```

Isso abre a tela de cadastro de hóspede, e de lá dá pra ir pra tela de reserva clicando no botão.

## Estrutura do código

O projeto tá dividido em 3 pacotes, seguindo a ideia de camadas. O `model` tem as classes que representam os dados (Hospede, Quarto, Data, Reserva e o enum TipoQuarto). O `business` tem o GerenciadorReservas, que cuida das regras de negócio. E o `presentation` tem os Controllers, que são as classes ligadas às telas .fxml.

## O que já funciona

Dá pra cadastrar hóspede (nome e cpf) e fazer reserva escolhendo hóspede, quarto e as datas num calendário. O sistema valida as duas regras que o trabalho pede: não deixa reservar quarto que já tá ocupado no período escolhido, e não deixa fazer checkout antes do checkin. Os quartos já vêm cadastrados de fábrica (5 quartos fixos, entre solteiro, casal e suíte).

## O que falta

Hoje os dados só ficam na memória enquanto o programa tá aberto, então fechando o sistema perde tudo, ainda preciso ver como salvar isso em arquivo ou banco. Também falta a tela de calendário de ocupação, os testes JUnit e o diagrama UML. Pretendo ir fazendo essas partes ao longo do semestre.

Hadnan Menezes