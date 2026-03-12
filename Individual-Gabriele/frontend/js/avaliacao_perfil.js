const API_AVALIACOES = "http://localhost:8080/avaliacoes";
const idUsuario = localStorage.getItem('id_usuario_logado');

window.onload = () => {
    if (!idUsuario) {
        window.location.href = 'index.html';
        return;
    }
    carregarMinhasAvaliacoes();
};

async function carregarMinhasAvaliacoes() {
    try {
        const response = await fetch(`${API_AVALIACOES}/usuario/${idUsuario}`);
        const container = document.getElementById('lista-avaliacoes');

        if (response.status === 204) {
            container.innerHTML = "<p style='text-align:center; color:#888;'>Você ainda não fez nenhuma avaliação.</p>";
            return;
        }

        const avaliacoes = await response.json();
        container.innerHTML = "";

        if (!avaliacoes || avaliacoes.length === 0) {
            container.innerHTML = "<p style='text-align:center; color:#888;'>Você ainda não fez nenhuma avaliação.</p>";
            return;
        }

        avaliacoes.forEach(ava => {
            console.log("Dados da avaliação:", ava);
            container.innerHTML += `
        <div class="card" style="margin-bottom: 20px;"> 
            <div style="display:flex; justify-content: space-between; align-items: flex-start;">
                <div>
                    <h4 style="color: white; margin-bottom: 5px;">${ava.idFilme?.titulo || 'Filme não identificado'}</h4>
                    <p style="color: #e50914; font-weight: bold;">Nota: ${ava.nota}</p>
                    <p style="color: #bbb; margin-top: 10px;">${ava.descricao || 'Sem comentário'}</p>
                </div>
                <div style="display:flex; flex-direction: column; gap: 10px;">
                    <button onclick="abrirModal(${ava.id}, ${ava.nota}, ${ava.favorito}, '${ava.descricao}')" 
                            style="padding: 5px 10px; font-size: 0.8rem; background: #007bff; color: white; border-radius: 4px;">Editar</button>
                    <button onclick="deletarAvaliacao(${ava.id})" 
                            style="padding: 5px 10px; font-size: 0.8rem; background: #dc3545; color: white; border-radius: 4px;">Excluir</button>
                </div>
            </div>
        </div>
    `;
        });
    } catch (error) {
        console.error("Erro ao carregar avaliações:", error);
    }
}

async function deletarAvaliacao(id) {
    if (!confirm("Deseja realmente excluir esta avaliação?")) return;

    try {
        const response = await fetch(`${API_AVALIACOES}/${id}`, { method: 'DELETE' });
        if (response.ok) {
            carregarMinhasAvaliacoes();
        }
    } catch (error) {
        console.error("Erro ao deletar:", error);
    }
}

function abrirModal(id, nota, favorito, descricao) {
    document.getElementById('edit-id').value = id;
    document.getElementById('edit-nota').value = nota;
    document.getElementById('edit-favorito').checked = favorito;
    document.getElementById('edit-descricao').value = descricao;
    document.getElementById('modalEdicao').style.display = 'flex';
}

function fecharModal() {
    document.getElementById('modalEdicao').style.display = 'none';
}

async function salvarEdicao() {
    const id = document.getElementById('edit-id').value;
    const dados = {
        nota: parseFloat(document.getElementById('edit-nota').value),
        favorito: document.getElementById('edit-favorito').checked,
        descricao: document.getElementById('edit-descricao').value
    };

    console.log("Tentando salvar ID:", id);
    console.log("Dados enviados:", dados);

    if (!id || id === "undefined") {
        alert("Erro: ID da avaliação não encontrado.");
        return;
    }

    try {
        const response = await fetch(`${API_AVALIACOES}/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dados)
        });

        if (response.ok) {
            alert("Avaliação atualizada!");
            fecharModal();
            carregarMinhasAvaliacoes();
        } else {
            const erroTxt = await response.text();
            console.error("Erro do servidor:", erroTxt);
            alert("Erro ao salvar no servidor.");
        }
    } catch (error) {
        console.error("Erro na requisição:", error);
    }
}