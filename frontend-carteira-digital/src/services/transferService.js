import api from "./api";

export const transferAmount = async (transferData) => {
    try {
    
        const response = await api.post(`/transfers`, transferData);
        return response.data;
    } catch (error) {
        console.error("Erro na chamada ao endpoint da transferência:", error);
        throw error;
    }
};

export const getTransferList = async () => {
    try {

        const response = await api.get(`/transfers/1`);
        return response.data;
    } catch (error) {
        console.error("Erro na chamada ao endpoint da transferência:", error);
        throw error;
    }
}
