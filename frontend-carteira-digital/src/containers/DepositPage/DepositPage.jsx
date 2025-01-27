import React, { useState } from "react";
import { PageContainer, Title, Button, Input, Paragraph } from "../Styles/styles";
import useDepositAmount from "../../hooks/useDepositAmount";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const DepositPage = () => {
    const [depositAmount, setDepositAmount] = useState("");
    const [account, setAccount] = useState(null);
    const [loading, setLoading] = useState(false);

    const accountId = 1;

    const handleDeposit = async () => {
        if (depositAmount && !isNaN(depositAmount)) {
            try {
                setLoading(true);
                
                if(depositAmount == 0) {
                    toast.warn("Por favor, insira um valor válido para depositar."); 
                    setDepositAmount("");
                    return;
                }
                const { account: updatedAccount } = await useDepositAmount(accountId, depositAmount);
                setAccount(updatedAccount);
                toast.success("Valor depositado com sucesso!"); 
                setDepositAmount("");
            } catch (error) {
                console.error("Erro ao realizar o depósito:", error);
                toast.error("Falha ao realizar o depósito. Tente novamente."); 
            } finally {
                setLoading(false);
            }
        } else {
            toast.warn("Por favor, insira um valor válido para depositar."); 
        }
    };

    return (
        <PageContainer>
            <Title>Depósito</Title>
            <p>Aqui você pode depositar dinheiro na sua conta</p>

            <Paragraph>Digite um valor que deseja depositar</Paragraph>
            <Input
                type="number"
                placeholder="Digite um valor"
                value={depositAmount}
                onChange={(e) => setDepositAmount(e.target.value)}
            />
            <Button onClick={handleDeposit}>Depositar</Button>
            {loading && <p>Processando o depósito...</p>}
            <ToastContainer />
        </PageContainer>
    );
};

export default DepositPage;
