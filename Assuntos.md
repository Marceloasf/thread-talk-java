## Introduzindo Threads no Java 

O Java suporta multithread a muito tempo utilizando a classe Thread. A Concurrency API foi introduzida para melhorar esse suporte (Java 5), a mesma inclui diversas classes para realizar tarefas complexas com base em threads.

A Concurrency API cresceu a longo dos anos e inclui inumeras classes e frameworks para ajudar com o desenvolvimento de aplicações multithread.

> **Nota:** A propriedade de executar multiplas threads e processos ao mesmo tempo é conhecida como *concurrency*.

### Tipos de Thread no Java

Todas as aplicações Java, por mais simples que sejam, são multithread. Até uma aplicação que realiza o print de um 'Hello World' é multithread. Para entender melhor isso, precisamos conhecer alguns tipos de thread:

- **System thread:** Criada pela JVM e executada no background da aplicação. Quando uma system thread encontra um problema e não se recupera, ela gera um Error, ao invés de uma Exception.
- **User-defined thread:** Criada pelo desenvolvedor para realizar uma tarefa especifica. 
- **Daemon thread:** Thread que não evita a JVM de ser fechada quando o programa é finalizado, uma aplicação Java é finalizada apenas quando as únicas threads sendo executadas são daemon threads.
  
### Criando Threads com Runnable e Thread

Runnable é uma interface funcional, ou seja, uma interface que possui apenas um método abstrato. Ela é normalmente utilizada para definir o que uma thread irá executar, a parte (separado) da thread main.

Como Runnable é uma interface funcional, nós podemos usar ela com apenas um lambda:
    
    Runnable x = () -> System.out.println("Runnable!");

A maneira mais simples de executar uma thread é usando a classe Thread. Para executar uma thread precisamos seguir dois passos, primeiro definimos a thread com a tarefa que ela vai executar e então executamos ela com o método `start()`.

### Polling com Sleep

## Criando Threads com a Concurrency API

>Java includes the Concurrency API to handle the complicated work of managing threads for you. This API includes the ExecutorService interface, which defines services that create and manage threads for you. It is recommended that you use this framework anytime you need to create and execute a separate task, even if you need only a single thread.

## Escrevendo código Thread-Safe

## Concurrent Collections

## Identificando Problemas com Threads

Um problema com threads pode ocorrer quando quando duas ou mais threads interagem de uma maneira inesperada e indesejada.

A Concurrency API foi criada para eliminar grande parte dos problemas com threads. Porém, mesmo assim podemos experienciar esses problemas.

### Liveness

Como visto anteriormente, muitas operações podem ser realizadas independentemente, mas algumas precisam de coordenação. Como por exemplo, utilizar o synchronizing ou CyclicBarrier, faz com que as threads esperem algo para continuar a execução.

A habilidade de uma aplicação executar em tempo hábil é conhecida como **Liveness**. Problemas com liveness são aqueles onde a aplicação fica travada ou sem dar resposta. Esses são três dos problemas mais comuns: deadlock, starvation e livelock.

- Deadlock: Acontece quando duas ou mais threads ficam bloqueadas para sempre, uma esperando a outra para continuar.
- Starvation: Ocorre quando uma única thread tem o acesso a um recurso ou lock constamente negado. 
- Livelock: Ocorre quando duas ou mais threads estão "bloqueadas para sempre", mesmo continuando ativas e tentando completar a tarefa. Muitas vezes ocorre quando duas threads estão tentando resolver um deadlock. 

### Race Condition

Race condition é um resultado indesejado que ocorre quando duas tarefas, que deveriam ser completadas sequencialmente, são completadas ao mesmo tempo.

Um caso que podemos usar de exemplo seria de dois usuários criando contas com o mesmo username. Nesse caso podemos ter três resultados:

- Ambos conseguem criar a conta com o mesmo username.
- Ambos não conseguem criar a conta com o mesmo username.
- Apenas um dos dois consegue criar a conta com o username.
