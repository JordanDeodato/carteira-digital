import { depositAmount } from "../services/depositService";

const useDepositAmount = async (accountId, depositData) => {
    try {
        const data = await depositAmount(accountId, { amount: depositData });
        return data;
    } catch (error) {
        console.error("Erro no serviço de depósito:", error);
        throw error;
    }
};

export default useDepositAmount;
