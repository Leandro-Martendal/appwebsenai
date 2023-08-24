document.addEventListener("DOMContentLoaded", function () {

    let comboPessoa = document.getElementById("pessoa");
    let comboPessoa1 = document.getElementById("pessoa1");
    let comboPessoa2 = document.getElementById("pessoa2");
    let comboPessoa3 = document.getElementById("pessoa3");

    let btnSaque = document.getElementById("btnSaque");
    let btnDeposito = document.getElementById("btnDeposito");
    let btnTransferir = document.getElementById("btnTransferir");

    function preencherPessoa() {
        fetch("/all")
            .then(response => response.json())
            .then(data => {
                let option = document.createElement("option")
                option.value = null;
                option.textContent = "";
                comboPessoa.appendChild(option);
                data.forEach(pessoa => {
                    let option = document.createElement("option")
                    option.value = pessoa.id;
                    option.textContent = pessoa.name;
                    comboPessoa.appendChild(option);
                })

            })
    }
    preencherPessoa();

    function preencherPessoa1() {
        fetch("/all")
            .then(response => response.json())
            .then(data => {
                let option = document.createElement("option")
                option.value = null;
                option.textContent = "";
                comboPessoa1.appendChild(option);
                data.forEach(pessoa1 => {
                    let option = document.createElement("option")
                    option.value = pessoa1.id;
                    option.textContent = pessoa1.name;
                    comboPessoa1.appendChild(option);
                })

            })
    }
    preencherPessoa1();

    function preencherPessoa2() {
        fetch("/all")
            .then(response => response.json())
            .then(data => {
                let option = document.createElement("option")
                option.value = null;
                option.textContent = "";
                comboPessoa2.appendChild(option);
                data.forEach(pessoa2 => {
                    let option = document.createElement("option")
                    option.value = pessoa2.id;
                    option.textContent = pessoa2.name;
                    comboPessoa2.appendChild(option);
                })

            })
    }
    preencherPessoa2();

    function preencherPessoa3() {
        fetch("/all")
            .then(response => response.json())
            .then(data => {
                let option = document.createElement("option")
                option.value = null;
                option.textContent = "";
                comboPessoa3.appendChild(option);
                data.forEach(pessoa3 => {
                    let option = document.createElement("option")
                    option.value = pessoa3.id;
                    option.textContent = pessoa3.name;
                    comboPessoa3.appendChild(option);
                })

            })
    }
    preencherPessoa3();

    function Saque(contaDestino, quantidade) {
        let parametros = new URLSearchParams();
        parametros.set('contaDestino', contaDestino)
        parametros.set('quantidade', quantidade)
        fetch("/sacarconta?" + parametros.toString(), {
            method: "Put"
        })
            .then(response => response.json())
            .then(data =>{
                document.getElementById("pessoa1").textContent = data.contaDestino;
                document.getElementById("deposito").textContent = data.quantidade;
            })

    }

    function Deposito(contaDestino, quantidade) {
        let parametros = new URLSearchParams();
        parametros.set('contaDestino', contaDestino)
        parametros.set('quantidade', quantidade)
        fetch("/depositarconta?" + parametros.toString(), {
            method: "Put"
        })
            .then(response => response.json())
            .then(data =>{
                document.getElementById("pessoa").textContent = data.contaDestino;
                document.getElementById("deposito").textContent = data.quantidade;
            })

    }

    function Transferir(contaOrigem, contaDestino, quantidade) {
        let parametros = new URLSearchParams();
        parametros.set('contaOrigem', contaOrigem)
        parametros.set('contaDestino', contaDestino)
        parametros.set('quantidade', quantidade)
        fetch("/transferirconta?" + parametros.toString(), {
            method: "Put"
        })
            .then(response => response.json())
            .then (data => {
                document.getElementById("pessoa2").textContent = data.contaOrigem;
                document.getElementById("pessoa3").textContent = data.contaDestino;
                document.getElementById("quantidade").textContent = data.quantidade;
            })
    };

    btnSaque.addEventListener("click", function () {
        let contaDestino = comboPessoa.value;
        let quantidade = parseFloat(document.getElementById("quantidade").value);
        Saque(contaDestino, quantidade);
    });

    btnDeposito.addEventListener("click", function () {
        let contaDestino = comboPessoa.value;
        let quantidade = parseFloat(document.getElementById("quantidade").value);
        Deposito(contaDestino, quantidade);
    });

    btnTransferir.addEventListener("click", function () {
        let contaOrigem = comboPessoa2.value;
        let contaDestino = comboPessoa3.value;
        let quantidade = parseFloat(document.getElementById("quantidade").value);
        Transferir(contaOrigem, contaDestino, quantidade);
    });
});