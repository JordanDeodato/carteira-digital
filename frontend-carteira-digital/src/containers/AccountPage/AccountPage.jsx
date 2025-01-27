import React from "react";
import { useParams } from "react-router-dom";
import AccountCard from "../../components/AccountCard";
import { PageContainer, Title, Message } from "../Styles/styles";
import useFetchAccount from "../../hooks/useFetchAccount"

const AccountPage = () => {
    const { account, loading } = useFetchAccount();

    if (loading) return <Message>Loading...</Message>;
    if (!account) return <Message>Nenhuma conta disponível.</Message>;

    return (
        <PageContainer>
            <Title>Minha Conta</Title>
            <AccountCard account={account} />
        </PageContainer>
    );
};

export default AccountPage;