import { transferAmount } from "../services/transferService";

const useTransferAmount = async (transferData) => {
    try {
        const data = await transferAmount(transferData);
        return data;
    } catch (error) {
        console.error("Erro no serviço da transferência:", error);
        throw error;
    }
};

export default useTransferAmount;
