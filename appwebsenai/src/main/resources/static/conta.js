document.addEventListener("DOMContentLoaded", function (){
    let comboPessoa = document.getElementById("pessoa");
    function preencherPessoa(){
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
});
btnConta.addEventListener("click", function (event){
    event.preventDefault();
    let formDados = new FormData(formPessoa);
    let parametros = new URLSearchParams(formDados);

    fetch("/criarconta?" + parametros.toString(), {
        method: "Post"
    }).then(response => response.json())
        .then(data => {
            document.getElementById("numero_conta").value = data.numero_conta;
        })
});