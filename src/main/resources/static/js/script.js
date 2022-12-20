function salvarUsuario() {
    let id = $("#id").val();
    let nome = $("#nome").val();
    let idade = $("#idade").val();
    let cep = $("#cep").val();
    let rua = $("#rua").val();
    let bairro = $("#bairro").val();
    let cidade = $("#cidade").val();
    let uf = $("#uf").val();

    if ((nome != null && nome.trim() != '') && (idade != null && idade > 0) && (cep != null && cep.trim() != '')) {
        $.ajax({
            method: "POST",
            url: "save",
            data: JSON.stringify(
                {
                    id: id,
                    nome: nome,
                    idade: idade,
                    endereco: {
                        cep: cep,
                        rua: rua,
                        bairro: bairro,
                        cidade: cidade,
                        uf: uf
                    },
                }
            ),
            contentType: "application/json; charset-utf8",
            success: function (response) {
                alert("Salvo com sucesso!");
                limpa();
                $(".tdEndereco").hide();
            }
        }).fail(function (xhr, status, errorThrown) {
            alert("Erro ao salvar: " + xhr.responseText);
        });
    } else {
        alert("Nome a idade e o cep devem ser preenchidos");
    }


}

$("#pesquisarUser").click(function () {
    let nome = $("#nomeBusca").val();

    if (nome != null && nome.trim() != '') {
       let request = $.ajax({
            method: "GET",
            url: "findbyname",
            data: "nome=" + nome,
            success: function (response) {
            
                $("#tabelaResultado > tbody > tr").remove();
                for (let i = 0; i < response.length; i++) {
                    $("#tabelaResultado > tbody").append(
                        '<tr id=\'' + response[i].id + '\'><td>' + response[i].nome + '</td><td>' + response[i].idade + '</td><td><button type="button" onclick="editar(\'' + response[i].id + '\')" class="btn btn-secondary">Ver</button></td><td><button type="button" class="btn btn-danger" onclick="deleteUser(\'' + response[i].id + '\')">Deletar</button></td></tr>');
                }
            }  
        }).fail(function (xhr, status, errorThrown) {
            alert("Erro ao Buscar Usuário: " + xhr.responseText);
        });
 
    } else {
        alert("Nome deve ser Informado");
    }
})



function editar(id) {

    $.ajax({
        method: "GET",
        url: "id",
        data: "id=" + id,
        success: function (response) {
            $(".tdEndereco").show();
            $("#id").val(response.id);
            $("#nome").val(response.nome);
            $("#idade").val(response.idade);
            $("#cep").val(response.endereco.cep);
            $("#rua").val(response.endereco.rua);
            $("#bairro").val(response.endereco.bairro);
            $("#cidade").val(response.endereco.cidade);
            $("#uf").val(response.endereco.uf);
            $("#exampleModal").modal('hide');

        }
    }).fail(function (xhr, status, errorThrown) {
        alert("Erro ao Buscar Usuário por id: " + xhr.responseText);
    });
}

$("#deleteUsertela").click(function () {
    let id = $("#id").val();
    if (id.trim() == '' || id == null) {
        alert("Usuário deve ser informado");
    } else {
        deleteUser(id);
        limpa();
    }
})


function deleteUser(id) {
    if (confirm('Deseja realmente Deletar?')) {
        $.ajax({
            method: "DELETE",
            url: "delete",
            data: "id=" + id,
            success: function (response) {
                $("#" + id).remove();
                alert(response);
            }
        }).fail(function (xhr, status, errorThrown) {
            alert("Erro ao Deletar Usuário por id: " + xhr.responseText);
        });
    }

}

function limpa() {
    $("#id").val("");
    $("#nome").val("");
    $("#idade").val("");
    $("#cep").val("");
    $("#rua").val("");
    $("#bairro").val("");
    $("#cidade").val("");
    $("#uf").val("");
    $(".tdEndereco").hide();
}
