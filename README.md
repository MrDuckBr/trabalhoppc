
## Descrição do Funcionamento do Algoritmo

O algoritmo em questão foi desenvolvido em java, linguagem essa que apresenta suporte a Threads e também conceitos de semáforos. No algoritmo contém quatro classes, onde a classe Principal é responsável exclusivamente por criar o objeto Estabelecimento este que de forma geral é responsável por toda a execução do código.
A classe Estabelecimento é a classe que cria as threads e também a classe que é responsável por ser a intermediária entre Cliente e Garçom, classes estas que também
fazem parte do programa. Cada objeto criado de cliente e garçom é criado uma thread como atributo dela, sendo assim podemos considerar que cada um deles é uma thread
Inicialmente a classe estabelecimento cria as threads referentes a cada cliente e garçom, onde cada um desses objetos estende a interface Runnable do Java, esta que permite que seja herdada características de Threads, essas classes então passam a implementar o método Run, responsável por sobrescrever o método da interface, método que é executado ao chamar o método start da thread criada.
O método Run é o ponto de entrada do processamento da thread, com isso todo comportamento que se espera que a thread irá realizar deve ser chamado direto ou indiretamente por ele. Dentro dessas classes é definido, todas as variáveis de controle das threads, assim como os métodos que definem o seu comportamento.
Para alguns métodos chaves é necessário que se faça a sincronização das threads para que previna que uma thread ou mais consiga executar o bloco de código em questão, para isso, a thread que for utilizar esse bloco adquire o lock associado ao objeto sincronizado e as demais que tentarem acessá-lo entrarão em estado de BLOCKED, até que o objeto seja liberado.
Detalhando um pouco mais o funcionamento do código, temos uma relação de dependência entre cliente e garçom, onde o garçom só executa alguma ação se e somente se existir um cliente que tenha feito um pedido, pedidos este que são feitos de forma randômica, então um cliente que está dentro do estabelecimento pode ou não realizar um pedido. Considerando que um pedido foi feito por um cliente, os garçons têm o início de suas tarefas.
Os garçons possuem limitações, como número máximo de pedidos que podem receber e número de pedidos que conseguem entregar de uma vez, sendo assim se um garçom está com o número de pedidos no máximo, automaticamente ele se torna indisponível para novos atendimentos naquela rodada. Ao finalizar a rodada, todas as variáveis de controle, tanto de clientes e garçons voltam ao estado inicial, se preparando para uma próxima rodada.
Todo o funcionamento do algoritmo é dado por meio de prints no console, desde a hora que uma thread cliente e thread garçom é criado, até a hora de finalizar uma rodada, deste modo é possível observar todo o fluxo das threads no algoritmo.


## Compilação do Programa

A compilação do programa é feita utilizando um compilador java (de preferência acima da versão 11) onde deve ser chamada o arquivo Principal.java e o mesmo chamará o restante dos arquivos necessários para que o programa funcione corretamente e os parâmetros devem ser passados utilizando espaço onde tais parâmetros são em ordem cliente, garçom,capacidade e por fim rodadas. Inicialmente deve-se compilar o mesmo utilizando o comando.

`javac Principal.java`

Em seguida, rodar o algoritmo com os parâmetros.

`java Principal 1 2 3 4`
