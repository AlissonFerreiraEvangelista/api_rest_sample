
function limpaFormulario() {
    $("#rua").val("");
    $("#bairro").val("");
    $("#cidade").val("");
    $("#uf").val("");
}

function buscaEndereco() {
    let cep = $("#cep").val().replace(/\D/g, ' ');
    let validacep = /^[0-9]{8}$/;
    if (cep != "") {
        if (validacep.test(cep)) {
            $.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?", function (dados) {

                if (!("erro" in dados)) {
                    $("#rua").val(dados.logradouro);
                    $("#bairro").val(dados.bairro);
                    $("#cidade").val(dados.localidade);
                    $("#uf").val(dados.uf);

                } else {
                    alert("CEP não encontrado.");
                    limpaFormulario();
                }
            })
        } else {
            alert("CEP invalido.");
        };
    } else {
        alert("Campo CEP em branco");
        limpaFormulario();
    }

}