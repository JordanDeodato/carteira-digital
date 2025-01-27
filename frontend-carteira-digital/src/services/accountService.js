import api from "./api"

export const getAccountData = async () => {
    const response = await api.get(`/accounts/1`);
    return response.data;
}