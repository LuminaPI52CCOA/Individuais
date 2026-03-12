const apiBaseUrl = "http://localhost:8080/api/corinthians";

// Carregar Dropdown via GET
window.onload = () => {
    fetch(`${apiBaseUrl}/setores`)
        .then(res => res.json())

        .then(setores => {
            const select = document.getElementById('setores');
            select.innerHTML = '<option value="">Escolha seu setor preferido</option>';
            setores.forEach(s => {
                select.innerHTML += `<option value="${s}">${s}</option>`;
            });
        })
        .catch(err => console.error("Erro ao buscar setores:", err));
};

// Enviar Cadastro via POST

document.getElementById('formCadastro').addEventListener('submit', function(e) {
    e.preventDefault();

    const respostaDiv = document.getElementById('cadastroResposta');
    const loadingDiv = document.getElementById('loading');
    const btnCadastrar = document.getElementById('btnCadastrar');

    // Limpando mensagens anteriores, mostrando o loading e desativando o botão para evitar múltiplos cliques
    respostaDiv.innerText = "";
    loadingDiv.style.display = "block";
    btnCadastrar.disabled = true;
    btnCadastrar.style.cursor = "not-allowed";
    btnCadastrar.style.opacity = "0.7"; // aspecto de desativado

    // Montando o objeto 
    const torcedor = {
        nome: document.getElementById('nome').value,
        email: document.getElementById('email').value,
        cpf: document.getElementById('cpf').value,
        dataNascimento: document.getElementById('dataNascimento').value,
        tipoPlano: document.querySelector('input[name="plano"]:checked').value,
        setorPreferidoEstadio: document.getElementById('setores').value,
        aceitaEmails: document.getElementById('aceitaEmails').checked // Pega true se estiver marcado, false se não
    };

    // Enviando o cadastro para o backend via fetch POST
    fetch(`${apiBaseUrl}/cadastrar`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(torcedor)
    })
    .then(res => {

        // Escondendo o loading e reativando o botão assim que a resposta chegar, independente do status
        loadingDiv.style.display = "none";
        btnCadastrar.disabled = false;
        btnCadastrar.style.cursor = "pointer";
        btnCadastrar.style.opacity = "1";

        if (res.status === 201) {
            respostaDiv.style.color = "lightgreen";
            respostaDiv.innerText = "Cadastro realizado com sucesso!";
            this.reset();
        } else {
            respostaDiv.style.color = "red";
            respostaDiv.innerText = "Erro no cadastro. Verifique os campos.";
        }
    })
    .catch(err => {
        // Escondendo o loading e reativando o botão mesmo em caso de erro de conexão
        loadingDiv.style.display = "none";
        btnCadastrar.disabled = false;
        btnCadastrar.style.cursor = "pointer";
        btnCadastrar.style.opacity = "1";

        respostaDiv.style.color = "red";
        respostaDiv.innerText = "Erro ao conectar com o servidor.";
    });
});