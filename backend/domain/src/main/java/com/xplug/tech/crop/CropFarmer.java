package com.xplug.tech.crop;

import com.xplug.tech.usermanager.UserAccount;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CropFarmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "crop_id", nullable = false)
    private Crop crop;

    @ManyToOne
    @JoinColumn(name = "User_account_id", nullable = false)
    private UserAccount userAccount; //farmer

    @ManyToOne
    @JoinColumn(name = "crop_program_id", nullable = false)
    private CropProgram cropProgram; //crop program to be used

    private LocalDate dateOfTransplant; //to derive crop stages and day of maturity

    private String location;

    private String remarks;

}
