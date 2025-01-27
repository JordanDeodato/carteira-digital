import React from "react";
import styled from "styled-components";

const Card = styled.div`
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 8px;
    background-color: #fafafa;
    text-align: center;
`;

const Balance = styled.p`
    font-size: 1.25rem;
    font-weight: bold;
    color: #333;
`;

const AccountCard = ({ account }) => {
    return (
        <Card>
            <p>ID da conta: {account.id}</p>
            <p>Titular: {account.user.name}</p>
            <Balance>Saldo: ${account.balance.toFixed(2)}</Balance>
        </Card>
    );
};

export default AccountCard;