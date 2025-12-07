const { createApp } = Vue;

createApp({
    data(){
        return{
            mesas: [],
            reserva: {
                numeroMesa: null,
                nomeCliente: "",
                data: ""
            },
            erro: "",
            sucesso: ""
        };
    },

    computed:{
        mesasDisponiveis(){
            return this.mesas.filter(m => m.disponivel === true);
        }
    },

    methods:{
        async buscarMesa(){
            try{
                const response = await fetch('/reservarestaurante/api/mesas?disponivel=true');
                const dados = await response.json();
                this.mesas = dados;
            } catch (err){
                this.erro = "Erro ao buscar mesas.";
            }
        },

        async enviarReserva(){
            try{
                this.erro = "";
                this.sucesso = "";

                const response = await fetch('/reservarestaurante/api/reservas', {
                    method: 'POST',
                    headers: { 'Content-type': 'application/json' },
                    body: JSON.stringify(this.reserva)
                });

                const dados = await response.json();
                this.respostaServidor(dados);

            } catch (err){
                this.erro = "Erro ao enviar reserva.";
            }
        },

        respostaServidor(resposta){
            if(resposta.sucesso){
                this.erro = "";
                this.sucesso = "Reserva realizada com sucesso!";
                this.limpar();
                this.buscarMesa();
            } else {
                this.sucesso = "";
                this.erro = resposta.mensagem || "Erro ao realizar reserva.";
            }
        },

        limpar(){
            this.reserva = {
                numeroMesa: null,
                nomeCliente: "",
                data: ""
            };
        }
    },

    mounted(){
        this.buscarMesa();
    }

}).mount("#app");
