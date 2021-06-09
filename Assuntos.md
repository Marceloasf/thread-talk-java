## Introduzindo Threads no Java 

O Java suporta multithread a muito tempo utilizando a classe Thread. A Concurrency API foi introduzida para melhorar esse suporte (Java 5), a mesma inclui diversas classes para realizar tarefas complexas com base em threads.

A Concurrency API cresceu a longo dos anos e inclui inumeras classes e frameworks para ajudar com o desenvolvimento de aplicações multithread.

> **Nota:** A propriedade de executar multiplas threads e processos ao mesmo tempo é conhecida como *concurrency*.

### Tipos de Thread

All the Java applications, including all of the ones that are presented in the book, are all multithreaded. Even a simple Java application that prints `Hello World` to the screen is multithreaded. To understand this, we need to be familiar with concepts of system threads and user-defined threads. 

- **System thread:** Criada pela JVM e executada no background da aplicação. Quando uma system thread encontra um problema e não se recupera, ela gera um Error, ao invés de uma Exception.
- **User-defined thread:** Criada pelo desenvolvedor para realizar uma tarefa especifica. 
- **Daemon thread:** Thread que não evita a JVM de ser fechada quando o programa é finalizado, uma aplicação Java é finalizada apenas quando as únicas threads sendo executadas são daemon threads.
  
### Criando Threads com Runnable e Thread

Runnable é uma interface funcional, ou seja, uma interface que possui apenas um método abstrato. Ela é normalmente utilizada para definir o que uma thread irá executar, a parte (separado) da thread main.

Como Runnable é uma interface funcional, nós podemos usar ela com apenas um lambda
    
    Runnable x = () -> System.out.println("TESTE");

A maneira mais simples de executar uma thread é usando a classe Thread. Para executar uma thread precisamos seguir dois passos, primeiro definimos a thread com a tarefa que ela vai executar e então executamos ela com o método `start()`.

## Criando Threads com a Concurrency API

Java includes the Concurrency API to handle the complicated work of managing threads for you. This API includes the ExecutorService interface, which defines services that create and manage threads for you. It is recommended that you use this framework anytime you need to create and execute a separate task, even if you need only a single thread.

## Escrevendo código Thread-Safe

## Concurrent Collections

## Identificando Problemas com Threads