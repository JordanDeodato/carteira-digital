import React from "react";
import { NavLink } from "react-router-dom";
import styled from "styled-components";

const HeaderContainer = styled.header`
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px 20px;
    background-color: #6200ea;
    color: #fff;
`;

const Nav = styled.nav`
    display: flex;
    gap: 15px;
`;

const StyledLink = styled(NavLink)`
    color: #fff;
    text-decoration: none;
    font-weight: bold;

    &.active {
        text-decoration: underline;
    }

    &:hover {
        opacity: 0.8;
    }
`;

const Header = () => {
    return (
        <HeaderContainer>
            <h1>MyBank</h1>
            <Nav>
                <StyledLink to="/">Home</StyledLink>
                <StyledLink to="/transferencia">Transferência</StyledLink>
                <StyledLink to="/deposito">Depósito</StyledLink>
                <StyledLink to="/conta">Conta</StyledLink>
                <StyledLink to="/lista-transferencia">Lista Transferências</StyledLink>
            </Nav>
        </HeaderContainer>
    );
};

export default Header;