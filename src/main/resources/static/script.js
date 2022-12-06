function salvarUsuario() {
    let id = $("#id").val();
    let nome = $("#nome").val();
    let idade = $("#idade").val();

    if((nome != null && nome.trim() != '') && (idade != null && idade > 0)){
        $.ajax({
            method: "POST",
            url: "save",
            data: JSON.stringify(
                {
                    id: id,
                    nome: nome,
                    idade: idade
                }
            ),
            contentType: "application/json; charset-utf8",
            success: function (response) {
                alert("Salvo com success");
                limpa();
            }
        }).fail(function (xhr, status, errorThrown) {
            alert("Erro ao salvar: " + xhr.responseText);
        });
    }else{
        alert("Nome e idade devem ser preenchidos");
    }
    
    
}

function pesquisarUser() {
    let nome = $("#nomeBusca").val();

    if (nome != null && nome.trim() != '') {
        $.ajax({
            method: "GET",
            url: "findbyname",
            data: "nome=" + nome,
            success: function (response) {
                $("#tabelaResultado > tbody > tr").remove();
                for (let i = 0; i < response.length; i++) {
                    $("#tabelaResultado > tbody").append(
                        '<tr><td>' + response[i].nome + '</td><td>' + response[i].idade + '</td><td><button type="button" onclick="editar(\''+ response[i].id+'\')" class="btn btn-secondary">Ver</button><td></tr>');
                }
            }
        }).fail(function (xhr, status, errorThrown) {
            alert("Erro ao Buscar Usuário: " + xhr.responseText);
        });
    }

}
function editar(id) {
    
    $.ajax({
        method: "GET",
        url: "id",
        data: "id=" + id,
        success: function (response) {
            $("#id").val(response.id);
            $("#nome").val(response.nome);
            $("#idade").val(response.idade);
            $("#exampleModal").modal('hide');
        }
    }).fail(function (xhr, status, errorThrown) {
        alert("Erro ao Buscar Usuário por id: " + xhr.responseText);
    });
}

function limpa() {
    $("#id").val("");
    $("#nome").val("");
    $("#idade").val("");
}
