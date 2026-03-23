const API_URL = window.location.origin;
const PERFIL_ID = 1;


const formPerfil = document.getElementById('formPerfil');
const formProjeto = document.getElementById('formProjeto');
const formHabilidade = document.getElementById('formHabilidade');
const formExperiencia = document.getElementById('formExperiencia');
const formContato = document.getElementById('formContato');


document.addEventListener('DOMContentLoaded', () => {
    formPerfil.addEventListener('submit', salvarPerfil);
    formProjeto.addEventListener('submit', salvarProjeto);
    formHabilidade.addEventListener('submit', salvarHabilidade);
    formExperiencia.addEventListener('submit', salvarExperiencia);
    formContato.addEventListener('submit', salvarContato);

    carregarPerfil();
    carregarProjetos();
    carregarHabilidades();
    carregarExperiencias();
    carregarContatos();
});


async function carregarPerfil() {
    try {
        const res = await fetch(`${API_URL}/api/perfis/${PERFIL_ID}`);
        if (!res.ok) throw new Error('Perfil não encontrado');
        const perfil = await res.json();

        document.getElementById('perfilId').value = perfil.id;
        document.getElementById('perfilNome').value = perfil.nome;
        document.getElementById('perfilTitulo').value = perfil.titulo;
        document.getElementById('perfilBio').value = perfil.bio;
        document.getElementById('perfilLogo').value = perfil.logo || '';

        document.getElementById('perfilInfo').innerHTML = `
            <p><strong>Status:</strong> Perfil carregado</p>
            <p><strong>Nome:</strong> ${perfil.nome}</p>
            <p><strong>Título:</strong> ${perfil.titulo}</p>
        `;
    } catch (e) {
        console.error('Erro ao carregar perfil:', e);
        document.getElementById('perfilInfo').innerHTML = '<p>Perfil não encontrado. Crie um novo!</p>';
    }
}

async function salvarPerfil(e) {
    e.preventDefault();
    const id = document.getElementById('perfilId').value;

    const perfil = {
        nome: document.getElementById('perfilNome').value,
        titulo: document.getElementById('perfilTitulo').value,
        bio: document.getElementById('perfilBio').value,
        logo: document.getElementById('perfilLogo').value
    };

    try {
        let res;
        if (id) {
            // Editar
            res = await fetch(`${API_URL}/api/perfis/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(perfil)
            });
        } else {
            // Criar
            res = await fetch(`${API_URL}/api/perfis`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(perfil)
            });
        }

        if (res.ok) {
            alert('Perfil salvo com sucesso!');
            carregarPerfil();
        }
    } catch (e) {
        alert('Erro ao salvar perfil: ' + e.message);
    }
}


async function carregarProjetos() {
    try {
        const res = await fetch(`${API_URL}/api/projetos/perfil/${PERFIL_ID}`);
        const projetos = await res.json();
        const lista = document.getElementById('listaProjetos');

        if (projetos.length === 0) {
            lista.innerHTML = '<p>Nenhum projeto cadastrado</p>';
            return;
        }

        lista.innerHTML = projetos.map(p => `
            <div class="card">
                <h3>${p.titulo}</h3>
                <p>${p.descricao}</p>
                <p><strong>Tecnologias:</strong> ${p.tecnologias}</p>
                ${p.linkGithub ? `<p><a href="${p.linkGithub}" target="_blank">🔗 GitHub</a></p>` : ''}
                ${p.linkDeploy ? `<p><a href="${p.linkDeploy}" target="_blank">🚀 Deploy</a></p>` : ''}
                <div class="actions">
                    <button onclick="editarProjeto(${p.id})" class="btn-edit">✏️ Editar</button>
                    <button onclick="deletarProjeto(${p.id})" class="btn-delete">🗑️ Deletar</button>
                </div>
            </div>
        `).join('');
    } catch (e) {
        console.error('Erro ao carregar projetos:', e);
    }
}

async function salvarProjeto(e) {
    e.preventDefault();
    const id = document.getElementById('projetoId').value;

    const projeto = {
        perfilId: PERFIL_ID,
        titulo: document.getElementById('projetoTitulo').value,
        descricao: document.getElementById('projetoDescricao').value,
        tecnologias: document.getElementById('projetoTecnologias').value,
        linkGithub: document.getElementById('projetoGithub').value,
        linkDeploy: document.getElementById('projetoDeploy').value
    };

    try {
        let res;
        if (id) {
            res = await fetch(`${API_URL}/api/projetos/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(projeto)
            });
        } else {
            res = await fetch(`${API_URL}/api/projetos`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(projeto)
            });
        }

        if (res.ok) {
            alert('Projeto salvo com sucesso!');
            formProjeto.reset();
            document.getElementById('projetoId').value = '';
            carregarProjetos();
        }
    } catch (e) {
        alert('Erro ao salvar projeto: ' + e.message);
    }
}

async function editarProjeto(id) {
    try {
        const res = await fetch(`${API_URL}/api/projetos/${id}`);
        const projeto = await res.json();

        document.getElementById('projetoId').value = projeto.id;
        document.getElementById('projetoTitulo').value = projeto.titulo;
        document.getElementById('projetoDescricao').value = projeto.descricao;
        document.getElementById('projetoTecnologias').value = projeto.tecnologias;
        document.getElementById('projetoGithub').value = projeto.linkGithub || '';
        document.getElementById('projetoDeploy').value = projeto.linkDeploy || '';

        abrirAba('projetos');
        formProjeto.scrollIntoView();
    } catch (e) {
        alert('Erro ao carregar projeto: ' + e.message);
    }
}

async function deletarProjeto(id) {
    if (!confirm('Tem certeza que deseja deletar este projeto?')) return;

    try {
        const res = await fetch(`${API_URL}/api/projetos/${id}`, { method: 'DELETE' });
        if (res.ok) {
            alert('Projeto deletado!');
            carregarProjetos();
        }
    } catch (e) {
        alert('Erro ao deletar projeto: ' + e.message);
    }
}


async function carregarHabilidades() {
    try {
        const res = await fetch(`${API_URL}/api/habilidades/perfil/${PERFIL_ID}`);
        const habilidades = await res.json();
        const lista = document.getElementById('listaHabilidades');

        if (habilidades.length === 0) {
            lista.innerHTML = '<p>Nenhuma habilidade cadastrada</p>';
            return;
        }

        lista.innerHTML = habilidades.map(h => `
            <div class="tag-item">
                <span class="tag">${h.nome} <em>(${h.nivel})</em></span>
                <div class="actions-inline">
                    <button onclick="editarHabilidade(${h.id})" class="btn-small">✏️</button>
                    <button onclick="deletarHabilidade(${h.id})" class="btn-small btn-danger">🗑️</button>
                </div>
            </div>
        `).join('');
    } catch (e) {
        console.error('Erro ao carregar habilidades:', e);
    }
}

async function salvarHabilidade(e) {
    e.preventDefault();
    const id = document.getElementById('habilidadeId').value;

    const habilidade = {
        perfilId: PERFIL_ID,
        nome: document.getElementById('habilidadeNome').value,
        nivel: document.getElementById('habilidadeNivel').value
    };

    try {
        let res;
        if (id) {
            res = await fetch(`${API_URL}/api/habilidades/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(habilidade)
            });
        } else {
            res = await fetch(`${API_URL}/api/habilidades`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(habilidade)
            });
        }

        if (res.ok) {
            alert('Habilidade salva com sucesso!');
            formHabilidade.reset();
            document.getElementById('habilidadeId').value = '';
            carregarHabilidades();
        }
    } catch (e) {
        alert('Erro ao salvar habilidade: ' + e.message);
    }
}

async function editarHabilidade(id) {
    try {
        const res = await fetch(`${API_URL}/api/habilidades/${id}`);
        const habilidade = await res.json();

        document.getElementById('habilidadeId').value = habilidade.id;
        document.getElementById('habilidadeNome').value = habilidade.nome;
        document.getElementById('habilidadeNivel').value = habilidade.nivel;

        abrirAba('habilidades');
        formHabilidade.scrollIntoView();
    } catch (e) {
        alert('Erro ao carregar habilidade: ' + e.message);
    }
}

async function deletarHabilidade(id) {
    if (!confirm('Tem certeza que deseja deletar esta habilidade?')) return;

    try {
        const res = await fetch(`${API_URL}/api/habilidades/${id}`, { method: 'DELETE' });
        if (res.ok) {
            alert('Habilidade deletada!');
            carregarHabilidades();
        }
    } catch (e) {
        alert('Erro ao deletar habilidade: ' + e.message);
    }
}


async function carregarExperiencias() {
    try {
        const res = await fetch(`${API_URL}/api/experiencias/perfil/${PERFIL_ID}`);
        const experiencias = await res.json();
        const lista = document.getElementById('listaExperiencias');

        if (experiencias.length === 0) {
            lista.innerHTML = '<p>Nenhuma experiência cadastrada</p>';
            return;
        }

        lista.innerHTML = experiencias.map(exp => `
            <div class="card">
                <h3>${exp.cargo}</h3>
                <p><strong>Empresa:</strong> ${exp.empresa}</p>
                <p><strong>Período:</strong> ${exp.dataInicio} até ${exp.dataFim}</p>
                <p>${exp.descricao}</p>
                <div class="actions">
                    <button onclick="editarExperiencia(${exp.id})" class="btn-edit">✏️ Editar</button>
                    <button onclick="deletarExperiencia(${exp.id})" class="btn-delete">🗑️ Deletar</button>
                </div>
            </div>
        `).join('');
    } catch (e) {
        console.error('Erro ao carregar experiências:', e);
    }
}

async function salvarExperiencia(e) {
    e.preventDefault();
    const id = document.getElementById('experienciaId').value;

    const experiencia = {
        perfilId: PERFIL_ID,
        cargo: document.getElementById('experienciaCargo').value,
        empresa: document.getElementById('experienciaEmpresa').value,
        dataInicio: document.getElementById('experienciaDataInicio').value,
        dataFim: document.getElementById('experienciaDataFim').value,
        descricao: document.getElementById('experienciaDescricao').value
    };

    try {
        let res;
        if (id) {
            res = await fetch(`${API_URL}/api/experiencias/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(experiencia)
            });
        } else {
            res = await fetch(`${API_URL}/api/experiencias`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(experiencia)
            });
        }

        if (res.ok) {
            alert('Experiência salva com sucesso!');
            formExperiencia.reset();
            document.getElementById('experienciaId').value = '';
            carregarExperiencias();
        }
    } catch (e) {
        alert('Erro ao salvar experiência: ' + e.message);
    }
}

async function editarExperiencia(id) {
    try {
        const res = await fetch(`${API_URL}/api/experiencias/${id}`);
        const experiencia = await res.json();

        document.getElementById('experienciaId').value = experiencia.id;
        document.getElementById('experienciaCargo').value = experiencia.cargo;
        document.getElementById('experienciaEmpresa').value = experiencia.empresa;
        document.getElementById('experienciaDataInicio').value = experiencia.dataInicio;
        document.getElementById('experienciaDataFim').value = experiencia.dataFim;
        document.getElementById('experienciaDescricao').value = experiencia.descricao;

        abrirAba('experiencias');
        formExperiencia.scrollIntoView();
    } catch (e) {
        alert('Erro ao carregar experiência: ' + e.message);
    }
}

async function deletarExperiencia(id) {
    if (!confirm('Tem certeza que deseja deletar esta experiência?')) return;

    try {
        const res = await fetch(`${API_URL}/api/experiencias/${id}`, { method: 'DELETE' });
        if (res.ok) {
            alert('Experiência deletada!');
            carregarExperiencias();
        }
    } catch (e) {
        alert('Erro ao deletar experiência: ' + e.message);
    }
}

// ==================== CONTATOS ====================
async function carregarContatos() {
    try {
        const res = await fetch(`${API_URL}/api/contatos/perfil/${PERFIL_ID}`);
        const contatos = await res.json();
        const lista = document.getElementById('listaContatos');

        if (contatos.length === 0) {
            lista.innerHTML = '<p>Nenhum contato cadastrado</p>';
            return;
        }

        lista.innerHTML = contatos.map(c => `
            <div class="contato-item">
                <a href="${c.url}" target="_blank" class="contato-link">${c.tipo}</a>
                <div class="actions-inline">
                    <button onclick="editarContato(${c.id})" class="btn-small">✏️</button>
                    <button onclick="deletarContato(${c.id})" class="btn-small btn-danger">🗑️</button>
                </div>
            </div>
        `).join('');
    } catch (e) {
        console.error('Erro ao carregar contatos:', e);
    }
}

async function salvarContato(e) {
    e.preventDefault();
    const id = document.getElementById('contatoId').value;

    const contato = {
        perfilId: PERFIL_ID,
        tipo: document.getElementById('contatoTipo').value,
        url: document.getElementById('contatoUrl').value
    };

    try {
        let res;
        if (id) {
            res = await fetch(`${API_URL}/api/contatos/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(contato)
            });
        } else {
            res = await fetch(`${API_URL}/api/contatos`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(contato)
            });
        }

        if (res.ok) {
            alert('Contato salvo com sucesso!');
            formContato.reset();
            document.getElementById('contatoId').value = '';
            carregarContatos();
        }
    } catch (e) {
        alert('Erro ao salvar contato: ' + e.message);
    }
}

async function editarContato(id) {
    try {
        const res = await fetch(`${API_URL}/api/contatos/${id}`);
        const contato = await res.json();

        document.getElementById('contatoId').value = contato.id;
        document.getElementById('contatoTipo').value = contato.tipo;
        document.getElementById('contatoUrl').value = contato.url;

        abrirAba('contatos');
        formContato.scrollIntoView();
    } catch (e) {
        alert('Erro ao carregar contato: ' + e.message);
    }
}

async function deletarContato(id) {
    if (!confirm('Tem certeza que deseja deletar este contato?')) return;

    try {
        const res = await fetch(`${API_URL}/api/contatos/${id}`, { method: 'DELETE' });
        if (res.ok) {
            alert('Contato deletado!');
            carregarContatos();
        }
    } catch (e) {
        alert('Erro ao deletar contato: ' + e.message);
    }
}


function abrirAba(abaName) {

    document.querySelectorAll('.aba').forEach(aba => aba.classList.add('hidden'));


    document.querySelectorAll('.tab-btn').forEach(btn => btn.classList.remove('active'));


    document.getElementById(abaName).classList.remove('hidden');


    event.target.classList.add('active');
}