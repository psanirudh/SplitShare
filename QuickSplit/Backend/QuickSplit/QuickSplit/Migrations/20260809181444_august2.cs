using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace QuickSplit.Migrations
{
    /// <inheritdoc />
    public partial class august2 : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Transaction_User_Userid",
                table: "Transaction");

            migrationBuilder.DropIndex(
                name: "IX_Transaction_Userid",
                table: "Transaction");

            migrationBuilder.DropColumn(
                name: "Userid",
                table: "Transaction");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<int>(
                name: "Userid",
                table: "Transaction",
                type: "int",
                nullable: true);

            migrationBuilder.CreateIndex(
                name: "IX_Transaction_Userid",
                table: "Transaction",
                column: "Userid");

            migrationBuilder.AddForeignKey(
                name: "FK_Transaction_User_Userid",
                table: "Transaction",
                column: "Userid",
                principalTable: "User",
                principalColumn: "id");
        }
    }
}
