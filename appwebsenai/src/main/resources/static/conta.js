document.addEventListener("DOMContentLoaded", function (){
    let comboPessoa = document.getElementById("pessoa");
    function preencherPessao(){
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
    preencherPessao();
});