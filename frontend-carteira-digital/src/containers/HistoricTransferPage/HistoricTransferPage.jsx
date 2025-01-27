import React from "react";
import { useParams } from "react-router-dom";
import TransferListCard from "../../components/TransferListCard";
import { PageContainer, Title, Message } from "../Styles/styles";
import useTransferList from "../../hooks/useTransferList";

const HistoricTransferPage = () => {
    const { transfer, loading } = useTransferList();

    if (loading) return <Message>Loading...</Message>;
    if (!transfer || transfer.length === 0) return <Message>Nenhuma transferência disponível.</Message>;
    
    return (
        <PageContainer>
            <Title>Lista de Transferências</Title>
            {transfer.map((transfer) => (
                <TransferListCard key={transfer.id} transfer={transfer} />
            ))}
        </PageContainer>
    );
};

export default HistoricTransferPage;