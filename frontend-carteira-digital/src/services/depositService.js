import api from "./api";

export const depositAmount = async (accountId, depositData) => {
    try {
        const response = await api.put(`/accounts/${accountId}`, depositData);
        return response.data;
    } catch (error) {
        console.error("Erro na chamada ao endpoint de depósito:", error);
        throw error;
    }
};
