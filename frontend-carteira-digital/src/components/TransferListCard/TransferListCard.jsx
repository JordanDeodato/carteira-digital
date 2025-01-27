import React from "react";
import { Card, Balance, Paragraph } from "./styles";

const TransferListCard = ({ transfer }) => {

    const formattedDate = new Date(transfer.transferDate).toLocaleDateString("pt-BR");
    return (
        <Card>
            <Balance>Valor transferido: R$ {transfer.amount}</Balance>
            <Paragraph>Para: {transfer.receiverAccountId.name}</Paragraph>
            <Paragraph>Data da transferência: {formattedDate}</Paragraph>
        </Card>
    );
};

export default TransferListCard;