Imports MySql.Data.MySqlClient
Public Class Dates
    Dim myconn As New MySqlConnection("Server=localhost;User Id=root;Password=030889;Database=appointments")
    Dim command As New MySqlCommand
    Dim command1 As New MySqlCommand

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click
        myconn = New MySqlConnection("Server=localhost;User Id=root;Password=030889;Database=appointments")
        Dim myread As MySqlDataReader
        Try
            myconn.Open()
            Dim query As String
            query = "insert into appointments.schedule(name,datebooked,timebooked,reason) values('" & TextBox1.Text & "','" & DateTimePicker1.Text & "','" & ComboBox1.Text & "','" & TextBox2.Text & "')"
            command = New MySqlCommand(query, myconn)
            myread = command.ExecuteReader
            Dim confirmation As String = "Your appointment has been booked on " & DateTimePicker1.Text & " for " & ComboBox1.Text
            MsgBox(confirmation)
            myconn.Close()
            Me.Refresh()

        Catch ex As MySqlException
            MsgBox(ex.Message)
        Finally
            myconn.Dispose()

        End Try
        Me.Close()
    End Sub
    

    Private Sub DateTimePicker1_ValueChanged(ByVal sender As Object, ByVal e As EventArgs) Handles DateTimePicker1.ValueChanged

        myconn = New MySqlConnection("Server=localhost;User Id=root;Password=030889;Database=appointments")
        Dim myread As MySqlDataReader
        DateTimePicker1.MinDate = Now
        Dim all_slots(9) As String
        all_slots(0) = "09:00-10:00"
        all_slots(1) = "10:00-11:00"
        all_slots(2) = "11:00-12:00"
        all_slots(3) = "12:00-13:00"
        all_slots(4) = "13:00-14:00"
        all_slots(5) = "14:00-15:00"
        all_slots(6) = "15:00-16:00"
        all_slots(7) = "16:00-17:00"
        all_slots(8) = "17:00-18:00"
        all_slots(9) = "18:00-19:00"


        Dim avail_slots As New List(Of String)()
        Dim booked_slots As New List(Of String)()
       
        Try
            myconn.Open()

            Dim query As String
            query = "Select * from schedule where datebooked='" & DateTimePicker1.Text & "'"
            command = New MySqlCommand(query, myconn)
            myread = command.ExecuteReader
            While myread.Read
                booked_slots.Add(myread.GetString("timebooked"))
                If booked_slots.Count = 10 Then
                    MsgBox("All slots for the selected date are booked.Please select other date")
                End If
            End While
            myconn.Close()

            For i As Integer = 0 To all_slots.Length - 1
                If Not booked_slots.Contains(all_slots(i)) Then
                    avail_slots.Add(all_slots(i))
                    
                End If
                
            Next
            ComboBox1.Items.Clear()

            For j As Integer = 0 To avail_slots.Count - 1
                ComboBox1.Items.Add(avail_slots.Item(j))
                
            Next


        Catch ex As MySqlException
            MsgBox(ex.Message)
        Finally
            myconn.Dispose()

        End Try
    End Sub

    Private Sub TextBox2_TextChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles TextBox2.TextChanged

    End Sub
End Class
