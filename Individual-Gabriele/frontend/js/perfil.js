const API_BASE = "http://localhost:8080/usuarios";
const idUsuario = localStorage.getItem('id_usuario_logado');

window.onload = async () => {
    if (!idUsuario) {
        window.location.href = 'index.html';
        return;
    }

    try {
        const response = await fetch(`${API_BASE}/${idUsuario}`);
        if (response.ok) {
            const usuario = await response.json();
            document.getElementById('perfil_nome').value = usuario.nome;
            document.getElementById('perfil_email').value = usuario.email;
        }
    } catch (error) {
        console.error("Erro ao carregar dados:", error);
    }
};

document.getElementById('formAtualizar').addEventListener('submit', async (e) => {
    e.preventDefault();

    const dadosAtualizados = {
        nome: document.getElementById('perfil_nome').value,
        senha: document.getElementById('perfil_senha').value
    };

    try {
        const response = await fetch(`${API_BASE}/${idUsuario}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dadosAtualizados)
        });

        if (response.ok) {
            window.location.reload();
        } else {
            alert("Erro ao atualizar dados.");
        }
    } catch (error) {
        console.error("Erro na requisição PUT:", error);
    }
});

async function deletarConta() {
    const confirmacao = confirm("Excluir dados e avaliações permanentemente.");

    if (confirmacao) {
        try {
            const response = await fetch(`${API_BASE}/${idUsuario}`, {
                method: 'DELETE'
            });

            if (response.ok) {
                localStorage.removeItem('id_usuario_logado');
                window.location.href = 'index.html';
            } else {
                alert("Erro ao excluir conta.");
            }
        } catch (error) {
            console.error("Erro na requisição DELETE:", error);
        }
    }
}

function voltar() {
    window.location.href = 'perfil.html';
}

function sair() {
    localStorage.removeItem('id_usuario_logado');
    window.location.href = 'index.html';
}