## Introduzindo Threads no Java 

O Java suporta multi-threaded a muito tempo utilizando a classe Thread. A Concurrency API foi introduzida para melhorar esse suporte (Java 5), a mesma inclui diversas classes para realizar tarefas complexas com base em threads.

A Concurrency API cresceu a longo dos anos e inclui inúmeras classes e frameworks para ajudar com o desenvolvimento de aplicações multithread.

> **Nota:** A propriedade de executar múltiplas threads e processos ao mesmo tempo é conhecida como *concurrency*.

### Tipos de Thread no Java

Todas as aplicações Java, por mais simples que sejam, são multithread. Até uma aplicação que realiza o print de um 'Hello World' é multithread. Para entender melhor isso, precisamos conhecer alguns tipos de thread:

- **System thread:** Criada pela JVM e executada no background da aplicação. Quando uma system thread encontra um problema e não se recupera, ela gera um Error, ao invés de uma Exception.
- **User-defined thread:** Criada pelo desenvolvedor para realizar uma tarefa específica.
- **Daemon thread:** Thread que não evita a JVM de ser fechada quando o programa é finalizado, uma aplicação Java é finalizada apenas quando as únicas threads sendo executadas são daemon threads.
  
### Criando Threads com Runnable e Thread

Runnable é uma interface funcional, ou seja, uma interface que possui apenas um método abstrato. Ela é normalmente utilizada para definir o que uma thread irá executar, a parte (separado) da thread main.

A maneira mais simples de executar uma thread é utilizando a classe Thread. Para executar uma thread precisamos seguir dois passos, primeiro definimos a thread com a tarefa que ela vai executar e então executamos ela com o método `start()`.

## Criando Threads com a Concurrency API

A Concurrency API foi criada para nós lidarmos com threads mais facilmente. Essa API inclui a interface `ExecutorService`, a qual define services que criam e gerenciam as threads para nós. É recomendado utilizar esse framework sempre que precisar executar uma tarefa com threads, até se for apenas com uma thread.

Para conseguirmos uma instância do objeto de um `ExecutorService`, precisamos utilizar a factory class `Executors` da Concurrency API.

### ExecutorService

O método mais simples da classe Executors seria o `newSingleThreadExecutor()`, esse método nós retorna uma instância de ExecutorService e nos permite utilizar o método `execute()` para submeter tarefas e executá-los de modo assíncrono.

Além do método `execute()`, temos o método `submit()`, que retorna uma instância de *Future*. Essa instância nos permite determinar se uma tarefa foi completa. Esses dois métodos são praticamente idênticos quando aplicados com Runnable. Porém é recomendado utilizar o `submit()` ao invés de `execute()`.

> **Nota:** O `submit()` possui implementações sobrecarregadas que aceitam Callable ao invés de Runnable.

Também é possível submeter collections de tarefas com os métodos `invokeAll()` e `invokeAny()`. Ambos executam as collections de tarefas de modo síncrono.

### Thread Executor Shutdown

Sempre lembre de chamar o `shutdown()` quando parar de usar um thread executor, pois um thread executor cria uma non-daemon thread assim que a primeira tarefa é executada. Caso não finalizado, pode levar a aplicação a nunca ser finalizada.

O processo de shutdown de um thread executor envolve três estados, sendo esses: active, shutting down e shutdown. É possível verificar esses estados com os métodos `isShutdown()` e `isTerminated()`.

### Outros Métodos da Classe Executors

Além do `newSingleThreadExecutor()` que foi apresentado acima, utilizando apenas uma thread, temos outros métodos que criam diferentes tipos de executors para nós, executors que podem agendar a execução de tarefas e executors que criam uma pool de threads por exemplo.

Alguns dos métodos mais comuns:
- ExecutorService newFixedThreadPool(int)
- ScheduledExecutorService newSingleThreadScheduledExecutor()
- ScheduledExecutorService newScheduledThreadPool(int)

A `ScheduledExecutorService` é uma subinterface da ExecutorService. Ela é útil para quando precisamos agendar uma tarefa para acontecer em algum momento futuro, se precisamos agendar a tarefa para acontecer repetidamente ou em algum intervalo definido.

O método `newFixedThreadPool(int)` recebe um número de threads e aloca todas elas no momento que é criado.

O método `newScheduledThreadPool(int)` é idêntico ao método `newFixedThreadPool(int)`, a diferença é que ele retorna uma instância de ScheduledExecutorService, o que permite agendar a execução de tarefas.

## Escrevendo código Thread-Safe

Thread-safety é a propriedade de um objeto que garante a execução segura por várias threads ao mesmo tempo.

### Atomic Classes

Uma maneira de melhorar nosso primeiro exemplo é utilizando as classes atômicas, que também fazem parte da Concurrency API.

Atômico é a propriedade de uma operação a ser realizada como uma única unidade de execução sem qualquer interferência de outra thread. Uma versão thread-safe atômica do exemplo seria incrementar e ler em apenas uma operação.

Essas classes possuem suporte para operações atômicas, onde qualquer thread tentando acessar a variável enquanto uma operação atômica estiver em andamento precisará esperar até que a operação atômica na variável seja concluída.

### Synchronized 

As classes atômicas são boas para variáveis, mas não se precisar executar uma série de comandos ou uma chamada de método. Para isso podemos usar um *monitor* que também é chamado de *lock* para sincronizar o acesso.

Um monitor é uma estrutura que suporta *mutual exclusion*, que é a propriedade de que no máximo **uma** thread está executando um pedaço de código em um determinado momento. 

No Java qualquer **Object** pode ser usado como um monitor, junto a palavra chave `synchronized`.

### Lock Framework

É muito similar ao uso do synchronize, porém ao invés de sincronizar em um Object, é possível fazer um "lock" em apenas um objeto que implementa a Lock interface.

Implementações equivalentes de Lock e synchronized:

    Object object = new Object();
    synchronized(object) {
    	// Código..
    }

    Lock lock = new ReentrantLock();
    try {
    	lock.lock(); 
    } finnaly {
    	lock.unlock();
    }

Essa classe ReentrantLock é um monitor simples e que suporta mutual exclusion, ou seja, no máximo uma thread pode segurar um lock.

### Orquestrando Tarefas com uma CyclicBarrier

Completamos a discussão sobre thread-safety discutindo como orquestrar tarefas complexas.

A classe CyclicBarrier nos permite realizar tarefas complexas multithread, enquanto todos os threads executam pedaços de código em paralelo, param e esperam nas barreiras lógicas.

## Identificando Problemas com Threads

Um problema com threads pode ocorrer quando duas ou mais threads interagem de uma maneira inesperada e indesejada.

A Concurrency API foi criada para eliminar grande parte dos problemas com threads. Porém, mesmo assim podemos experienciar esses problemas.

### Liveness

Como visto anteriormente, muitas operações podem ser realizadas independentemente, mas algumas precisam de coordenação. Como por exemplo, utilizar o synchronizing ou CyclicBarrier, faz com que as threads esperem algo para continuar a execução.

A habilidade de uma aplicação executar em tempo hábil é conhecida como **Liveness**. Problemas com liveness são aqueles onde a aplicação fica travada ou sem dar resposta. Esses são três dos problemas mais comuns: deadlock, starvation e livelock.

- Deadlock: Acontece quando duas ou mais threads ficam bloqueadas para sempre, uma esperando a outra para continuar.
- Starvation: Ocorre quando uma única thread tem o acesso a um recurso ou lock constantemente negado. 
- Livelock: Ocorre quando duas ou mais threads estão "bloqueadas para sempre", mesmo continuando ativas e tentando completar a tarefa. Muitas vezes ocorre quando duas threads estão tentando resolver um deadlock. 

### Race Condition

Race condition é um resultado indesejado que ocorre quando duas tarefas, que deveriam ser completadas sequencialmente, são completadas ao mesmo tempo.

Um caso que podemos usar de exemplo seria de dois usuários criando contas com o mesmo username. Nesse caso podemos ter três resultados:

- Ambos conseguem criar a conta com o mesmo username.
- Ambos não conseguem criar a conta com o mesmo username.
- Apenas um dos dois consegue criar a conta com o username.
