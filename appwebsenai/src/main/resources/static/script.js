const formPessoa= document.getElementById("cadastroPessoa");
const tabelaPessoa= document.getElementById("tabelaPessoas").getElementsByTagName('tbody')[0];
const btnAll= document.getElementById("listarPessoas");
const btnDelete = document.getElementById("excluir");

formPessoa.addEventListener("submit", function (event){
   event.preventDefault();
   let formDados = new FormData(formPessoa);
   let parametros = new URLSearchParams(formDados);

   fetch("/person?" + parametros.toString(), {
      method: "Post"
   }).then(response => response.json())
       .then(data => {
          document.getElementById("id").value = data.id;
       })
});

btnDelete.addEventListener("click", function (){
    fetch("/person?name=" + document.getElementById("nome").value, {
        method: "Delete"
    }).then(response => response.json())
});

btnAll.addEventListener("click", function (){
   fetch("/all")
       .then(response => response.json())
       .then(data => {
          tabelaPessoa.innerHTML = "";
          data.forEach(pessoa => {
            let row = tabelaPessoa.insertRow();
            row.insertCell(0).textContent = pessoa.id;
            row.insertCell(1).textContent = pessoa.name;
            row.insertCell(2).textContent = pessoa.sexo;

          })
       })
});