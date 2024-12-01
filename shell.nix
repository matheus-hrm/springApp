{ pkgs ? import <nixpkgs> {} }:

pkgs.mkShell {
    name = "java-spring-boot-shell";

    buildInputs = with pkgs; [
        maven
        postgresql
        lombok   
    ];

    shellHook = ''
        echo "Initializing environment..."

        export PGHOST=localhost
        export PGPORT=5432
        export PGUSER=postgres
        export PGPASSWORD=postgres
        export PGDATABASE=springbootdb

        echo "PostgreSQL environment variables set:"
        echo "  PGHOST=$PGHOST"
        echo "  PGPORT=$PGPORT"
        echo "  PGUSER=$PGUSER"
        echo "  PGDATABASE=$PGDATABASE"
    '';
}
