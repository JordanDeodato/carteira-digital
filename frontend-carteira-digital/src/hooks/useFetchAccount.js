import { useEffect } from "react";
import { useState } from "react";
import { getAccountData } from "../services/accountService";

const useFetchAccount = () => {
    const [account, setAccount] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchAccount = async () => {
            try {
                const data = await getAccountData();
                setAccount(data);
            } catch (error) {
                console.error("Failed to fetch account data:", error);
            } finally {
                setLoading(false);
            }
        };

        fetchAccount();

    }, []);  
    
    return { account, loading };
    
};

export default useFetchAccount;