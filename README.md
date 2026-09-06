# Trabalho de Hotel - POO

Sistema de gerenciamento de hotel, feito para a disciplina de POO do 3º semestre (IFBA-SAJ).

## Sobre o projeto

Fiz esse sistema pra gerenciar hóspedes, quartos e reservas de um hotel, com calendário de ocupação, login com controle de acesso e uma tela de auditoria. Usei Java com JavaFX pra interface gráfica, Maven pra gerenciar o projeto e Scene Builder pra desenhar as telas (arquivos .fxml).

## Como rodar

Precisa ter JDK 21+ e Maven instalados. Entra na pasta `trabalho-hotel` pelo terminal e roda `mvn clean install` seguido de `mvn javafx:run`. Isso abre a tela de login. Deixei um usuário administrador já cadastrado de fábrica pra facilitar o teste, com email `admin@hotel.com` e senha `123`. Depois de logar, abre a tela principal com os botões de navegação: usuário comum vê Cadastro, Reserva, Quartos, Calendário e Sair, enquanto o admin vê tudo isso e também Auditoria e Usuários.

## Estrutura do código

Organizei o projeto em quatro pacotes, seguindo a ideia de camadas. O `model` tem as classes que representam os dados, como Hospede, Quarto, Reserva e Usuario. O `data` tem os DAOs, que cuidam de salvar e buscar cada entidade, tudo guardado em memória com HashMap. O `business` tem os Services, responsáveis pelas regras de negócio, validação e autenticação. E o `presentation` tem os Controllers, ligados às telas .fxml. Pra evitar repetir código, todo model herda de uma classe genérica chamada AbstractModel, que já guarda id, data de criação, data de atualização, data de exclusão e quem fez cada uma dessas ações. Os DAOs e Services também têm uma versão genérica que toda entidade reaproveita, cada uma só sobrescrevendo o que é específico dela.

## Funcionalidades

O sistema permite cadastrar, editar e excluir hóspedes, quartos e reservas, cada um com sua própria tela e tabela. Pra fazer uma reserva, escolho o hóspede, o quarto e as datas de checkin e checkout, e o sistema valida as duas regras principais: não deixa reservar um quarto que já está ocupado no período escolhido, e não deixa fazer checkout antes do checkin. Também tem uma tela de calendário de ocupação, que mostra os dias do mês atual marcados de verde ou vermelho, dependendo se o quarto escolhido está livre ou ocupado naquele dia.

Do lado de autenticação, tem uma tela de login com email e senha, e um botão de sair que volta pra tela de login sem fechar o programa. O cadastro de usuários permite escolher se a pessoa vai ter perfil comum ou administrador, e só quem é administrador consegue ver os botões de Auditoria e de cadastro de Usuários.

Quando eu excluo um registro, ele não desaparece de vez do sistema: fica marcado como excluído, guardando quem excluiu e quando, some das listas normais mas continua existindo pra fins de auditoria. Por falar nisso, a tela de Auditoria (que só o administrador vê) tem um seletor pra escolher entre Hóspedes, Quartos, Reservas ou Usuários, e mostra pra cada registro quem criou, quando criou, quem alterou por último e quando foi essa última alteração. Tudo isso é preenchido automaticamente: toda vez que algo é salvo ou atualizado no sistema, o Service genérico já grava sozinho quem estava logado no momento.

Hadnan Menezes
