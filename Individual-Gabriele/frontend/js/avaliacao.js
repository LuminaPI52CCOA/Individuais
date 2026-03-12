const selectFilmes = document.getElementById('selectFilmes');
const API_BASE = "http://localhost:8080";

async function carregarDadosUsuario() {
    const idUsuario = localStorage.getItem('id_usuario_logado');
    const spanNome = document.getElementById('nome_usuario_header');

    if (!idUsuario) {
        window.location.href = 'index.html';
        return;
    }

    try {
        const response = await fetch(`${API_BASE}/usuarios/${idUsuario}`);
        if (response.ok) {
            const usuario = await response.json();
            if (spanNome) {
                spanNome.textContent = `Olá, ${usuario.nome}!`;
            }
        }
    } catch (error) {
        console.error("Erro ao buscar dados do usuário:", error);
    }
}

async function carregarFilmes() {
    try {
        const response = await fetch(`${API_BASE}/filmes`);
        const filmes = await response.json();

        console.log("Filmes recebidos da API:", filmes);

        selectFilmes.innerHTML = '<option value="">Selecione um filme</option>';

        filmes.forEach(filme => {
            const option = document.createElement('option');

            const id = filme.idFilme || filme.id;
            const titulo = filme.titulo || filme.nome || filme.titulo_filme;

            if (id && titulo) {
                option.value = id;
                option.textContent = titulo;
                selectFilmes.appendChild(option);
            }
        });
    } catch (error) {
        console.error("Erro ao carregar filmes:", error);
    }
}

document.getElementById('formFilme').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const novoFilme = {
        titulo: document.getElementById('titulo_filme').value,
        genero: document.getElementById('genero_filme').value,
        classInd: parseInt(document.getElementById('class_ind').value)
    };

    try {
        const response = await fetch(`${API_BASE}/filmes`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(novoFilme)
        });

        if (response.status === 201) {
            document.getElementById('formFilme').reset();
            await carregarFilmes();
        } else {
            alert("Erro ao cadastrar filme.");
        }
    } catch (error) {
        console.error("Erro na requisição de cadastro de filme:", error);
    }
});

document.getElementById('formAvaliacao').addEventListener('submit', async (e) => {
    e.preventDefault();

    const idFilmeSelecionado = selectFilmes.value;
    if (!idFilmeSelecionado) {
        alert("Por favor, selecione um filme na lista!");
        return;
    }

    const idUsuario = localStorage.getItem('id_usuario_logado') || 1;

    const dadosAvaliacao = {
        nota: parseFloat(document.getElementById('nota').value),
        favorito: document.getElementById('favorito').checked,
        descricao: document.getElementById('descricao').value,

        fk_filme: parseInt(idFilmeSelecionado),
        fk_usuario: parseInt(idUsuario)
    };

    try {
        const response = await fetch(`${API_BASE}/avaliacoes`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dadosAvaliacao)
        });

        if (response.status === 201) {
            document.getElementById('formAvaliacao').reset();
        } else {
            alert("Erro ao enviar avaliação. Verifique os campos.");
        }
    } catch (error) {
        console.error("Erro na requisição de avaliação:", error);
    }
});

function logout() {
    localStorage.removeItem('id_usuario_logado');
    window.location.href = 'index.html';
}

window.onload = () => {
    carregarFilmes();
    carregarDadosUsuario();
};  