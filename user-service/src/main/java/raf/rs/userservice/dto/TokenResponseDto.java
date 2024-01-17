package raf.rs.userservice.dto;

public class TokenResponseDto {

    private String jwt;

    public TokenResponseDto(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
