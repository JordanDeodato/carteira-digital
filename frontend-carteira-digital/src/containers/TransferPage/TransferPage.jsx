import React, { useState } from "react";
import { toast, ToastContainer } from "react-toastify";
import { PageContainer, Title, Button, Input, Paragraph } from "../Styles/styles";
import useTransferAmount from "../../hooks/useTransferAmount";
import "react-toastify/dist/ReactToastify.css";

const TransferPage = () => {
    const [senderAccountId, setSenderAccountId] = useState(1); 
    const [receiverCpf, setReceiverCpf] = useState("");
    const [transferAmount, setTransferAmount] = useState("");
    const [loading, setLoading] = useState(false);

    const handleTransfer = async () => {
        if (transferAmount && !isNaN(transferAmount)) {
            try {
                setLoading(true);

                if (transferAmount == 0) {
                    toast.warn("Por favor, insira um valor válido para transferir.");
                    setTransferAmount("");
                    return;
                }

                const transferData = {
                    senderAccountId: parseInt(senderAccountId),
                    receiverCpf: parseInt(receiverCpf),
                    amount: parseFloat(transferAmount), 
                };
                

                const { transfer: updatedTransfer } = await useTransferAmount(transferData);
                
                setTransferAmount(updatedTransfer);
                toast.success("Transferência realizada com sucesso!");
                setTransferAmount("");
                setReceiverCpf(""); 
            } catch (error) {
                console.error("Erro ao realizar a transferência:", error);
                toast.error("Falha ao realizar a transferência. Tente novamente.");
            } finally {
                setLoading(false);
            }
        } else {
            toast.warn("Por favor, insira um valor válido para transferir.");
        }
    };

    return (
        <PageContainer>
            <Title>Transferência</Title>
            <p>Aqui você pode transferir dinheiro para quem quiser.</p>

            <Paragraph>Para quem deseja transferir?</Paragraph>
            <Input
                type="number"
                placeholder="CPF do destinatário"
                value={receiverCpf}
                onChange={(e) => setReceiverCpf(e.target.value)}
            />

            <Paragraph>Digite um valor para transferir.</Paragraph>
            <Input
                type="number"
                placeholder="Digite um valor para transferir"
                value={transferAmount}
                onChange={(e) => setTransferAmount(e.target.value)}
            />

            <Button onClick={handleTransfer}>Transferir</Button>
            {loading && <p>Processando a transferência...</p>}

            <ToastContainer />
        </PageContainer>
    );
};

export default TransferPage;
