import { useEffect, useState } from "react";
import { getTransferList } from "../services/transferService";

export const useTransferList = () => {
    const [transfer, setTransfer] = useState(null);
    const [loading, setLoading] = useState(null);

    useEffect(() => {
        const fetchTransferList = async () => {
            try {
                const data = await getTransferList();
                setTransfer(data); 
            } catch (error) {
                console.error("Failed to fetch list transfer data:", error);
            } finally {
                setLoading(false);
            }
        };

        fetchTransferList();
    }, []);

    return { transfer, loading };
}

export default useTransferList;