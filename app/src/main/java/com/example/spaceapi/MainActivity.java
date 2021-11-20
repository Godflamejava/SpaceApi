package com.example.spaceapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recycleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        String name = intent.getStringExtra("name");
        TextView title=findViewById(R.id.title);
        if(name==null)
            name="Learner";
        title.setText("Welcome "+name+"!");
        ImageView logout= findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent out = new Intent(MainActivity.this,Start.class);
                out.putExtra("code","exit");
                startActivity(out);
            }
        });
        recycleView = findViewById(R.id.recycleview);
        ArrayList<Satellite> satellites=new ArrayList<>();
        ArrayList<String> names= new ArrayList<>();
        ArrayList<String> description= new ArrayList<>();
        ArrayList<String> id= new ArrayList<>();

        names.add("ISS");
        description.add("The International Space Station (ISS) is a joint project of five space agencies: the National Aeronautics and Space Administration (United States), the Russian Federal Space Agency (Russian Federation), the Japan Aerospace Exploration Agency (Japan), the Canadian Space Agency (Canada) and the European Space Agency (Europe). It is serviced primarily by the Soyuz, Progress spacecraft units and possible private missions in near future. Last Space Shuttle mission that serviced the Space Station ended in July 2011 (Atlantis, STS-135). The ISS is expected to remain in operation until at least 2020, and potentially to 2028.");
        id.add("25544");


        names.add("SES 1");
        description.add("SES 1 is a communications satellite designed to replace two aging spacecraft serving North America. It will reach its permananet slot in the geostationary arc at 101 degrees west longitude, where it will enter service in about a month for SES World Skies.");
        id.add("36516");

        names.add("NOAA 19");
        description.add("NOAA 19 is the fifth in a series of five Polar-orbiting Operational Environmental Satellites (POES) with advanced microwave sounding instruments that provide imaging and sounding capabilities. Circling 530 statute miles [850 km] above Earth and completing a revolution every 100 minutes, the NOAA-N Prime will operate in the so-called \"afternoon\" polar orbit to replace NOAA-18 and its degraded instruments. The orbit crosses the equator from south to north at 2 p.m. on the trips around the planet. NOAA-N Prime is outfitted with instruments that provide imagery, atmospheric temperature and humidity profiles, and land and ocean surface temperature observations, all of which are key ingredients for weather forecasting. In addition, the information generates decades-long databases for climate monitoring and global change studies. In addition, the NOAA satellites are equipped with search and rescue packages that detect distress signals from emergency beacons. Over the past 26 years, the network has been credited with more than 24,000 rescues worldwide");
        id.add("33591");

        names.add("GOES 13");
        description.add("GOES 13 is an American (NOAA) geostationary weather satellite that was launched by a Delta 4 rocket from Cape Canaveral at 22:11 UT on 24 May 2006. The 3133 kg (with fuel), 2.3 kW craft carries the usual set of GOES monitors: imager, sounder, SEM package, X-ray imager, energetic particle detector, and ground-data relaying equipment. The parking longitude is yet to be finalized");
        id.add("29155");

        names.add("NOAA 15");
        description.add("NOAA 15 (designated NOAA-K before launch) is one of the NASA-provided TIROS series of weather forecasting satellite run by NOAA. The satellite is placed in a sun-synchronous orbit, 807 km above the Earth, orbiting every 101 minutes. It hosts the AMSU-A and AMSU-B instruments, the AVHRR and High Resolution Infrared Radiation Sounder (HIRS/3) instruments, as well as a Space Environment Monitor (SEM/2). APT transmission frequency is 137.62 MHz. Due to problems with the S-band transmitter high-gain antennas, NOAA-15 has been configured for High Resolution Picture Transmission using the S-Band Transmitter #2 (1702.5 MHz) omnidirectional antenna.");
        id.add("25338");

        names.add("NOAA 18");
        description.add("NOAA 18, known before launch as NOAA-N, is a weather forecasting satellite run by NOAA. NOAA-N (18) was launched into a sun-synchronous orbit at an altitude of 854 km above the Earth, with an orbital period of 102 minutes. It hosts the AMSU-A, MHS, AVHRR, Space Environment Monitor SEM/2 instrument and High Resolution Infrared Radiation Sounder (HIRS) instruments, as well as the SBUV/2 ozone-monitoring instrument. NOAA 18 is the first NOAA POES satellite to use MHS in place of AMSU-B. APT transmission frequency is 137.9125 MHz (NOAA-18 changed frequencies with NOAA-19 on June 23, 2009).");
        id.add("28654");

        names.add("TERRA");
        description.add("TERRA (EOS AM-1) is a multi-national NASA scientific research satellite in a Sun-synchronous orbit around the Earth. It is the flagship of the Earth Observing System (EOS). The name \"TERRA\" comes from the Latin word for Earth. The satellite was placed into a near-polar, sun-synchronous orbit at an altitude of 705 km, with a 10:30am descending node. TERRA carries a payload of five remote sensors designed to monitor the state of Earth's environment and ongoing changes in its climate system: ASTER (Advanced Spaceborne Thermal Emission and Reflection Radiometer); CERES (Clouds and the Earth's Radiant Energy System); MISR (Multi-angle Imaging SpectroRadiometer); MODIS (Moderate-resolution Imaging Spectroradiometer); MOPITT (Measurements of Pollution in the Troposphere). Data from the satellite helps scientists better understand the spread of pollution around the globe. Studies have used instruments on TERRA to examine trends in global carbon monoxide and aerosol pollution. The data collected by TERRA will ultimately become a new, 15-year global data set.");
        id.add("25994");

        names.add("AQUA");
        description.add("AQUA (EOS-PM1) is an afternoon equator-crossing platform which includes a suite of sensors designed to study the diurnal properties of cloud and aerosol radiative fluxes, cloud formation, and precipitation (MIMR, AIRS/AMSU-A/MHS, and MODIS-N) in conjunction with a wind scatterometer planned for the Japanese ADEOS-II spacecraft. MIMR, MODIS-N, and AIRS will contribute to studies of sea-ice extent and heat exchange with the atmosphere. Data from the EOS-PM1 platform and the sensors on the TRMM spacecraft will combined to assess the utility and accuracy of precipitation measurements");
        id.add("27424");

        names.add("METOP-B");
        description.add("METOP-B is a 9,005-pound (4,085-kg) spacecraft outfitted with eight instruments to survey clouds, winds, moisture, greenhouse gases, and other atmospheric conditions for at least five years.");
        id.add("38771");

        names.add("SUOMI NPP");
        description.add("SUOMI NPP, previously known as the National Polar-orbiting Operational Environmental Satellite System Preparatory Project (NPP) and NPP-Bridge, is a weather satellite operated by the NOAA. Originally intended as a pathfinder for the NPOESS programme, which was to have replaced NOAA's Polar Operational Environmental Satellites and the U.S. Air Force's Defense Meteorological Satellite Program, Suomi was launched in 2011 after the cancellation of NPOESS to serve as a gapfiller between the POES satellites and the Joint Polar Satellite System which will replace them. Its instruments provide climate measurements that continue prior observations by NASA's Earth Observing System. The satellite is named after Verner E. Suomi, a meteorologist at the University of Wisconsin–Madison. The name was announced on January 24, 2012, three months after the satellite's launch. The satellite was placed into a sun-synchronous orbit 824 km (512 miles) above the Earth.");
        id.add("37849");


        names.add("GOES 15");
        description.add("GOES 15 (GOES-P) is an American weather satellite, which will form part of the Geostationary Operational Environmental Satellite (GOES) system operated by the US National Oceanic and Atmospheric Administration. The spacecraft was constructed by Boeing, and is the last of three GOES satellites to be based on the BSS-601 bus. In addition to weather forecasting on Earth, a key instrument onboard GOES-P, the Solar X-Ray Imager (SXI), will help NOAA continue monitoring solar conditions.");
        id.add("36411");

        names.add("FOX-1A (AO-85)");
        description.add("FOX-1A is a small 1-Unit CubeSat developed by AMSAT. The satellite was designed to take advantage of large and growing interest in CubeSats. AMSAT developed a strategy to create a family of CubeSats that would be attractive for flying science missions which would enable partnerships with universities and apply for free launches as science missions. This provided a solution for being unable to fly due to exponentially increasing launch costs. After the science mission, or even during the science mission, the satellite is capable of running its amateur radio transponder. The Fox Engineering Team arrived at a design which enables simultaneous amateur radio and scientific operations. The satellite includes an analog FM repeater that allows simple ground stations using an handheld FM transceiver and a simple antenna to make contacts using the FOX-1A.");
        id.add("40967");

        names.add("SAUDISAT 1C");
        description.add("SAUDISAT 1C (or SO-50, Saudi-OSCAR 50) carries several experiments, including a mode J FM amateur repeater experiment operating on 145.850 MHz uplink and 436.795 MHz downlink. The repeater is available to amateurs worldwide as power permits, using a 67.0 Hertz PL tone on the uplink, for on-demand activation. SO-50 also has a 10 minute timer that must be armed before use. Thus, first transmit an initial carrier with a PL tone of 74.4 to arm the timer. The repeater consists of a miniature VHF receiver with sensitivity of -124dBm, having an IF bandwidth of 15 kHz. The receive antenna is a 1/4 wave vertical mounted in the top corner of the spacecraft. The receive audio is filtered and conditioned then gated in the control electronics prior to feeding it to the 250 mW UHF transmitter. The downlink antenna is a 1/4 wave mounted in the bottom corner of the spacecraft and canted at 45 degrees inward.");
        id.add("27607");

        names.add("KMS-4");
        description.add("KMS-4 (Kwangmyongsong-4 meaning Bright Star-4 or Lodestar-4 in Korean) is an earth observation satellite launched by North Korea on 7 February 2016. The launch happened after North Korea conducted a nuclear test on 6 January and as the United Nations Security Council is deciding on sanctions to be placed on the country following the nuclear test. The launch was also timed to celebrate the 74th birthday of late leader Kim Jong-il on February 16. Details on the Kwangmyongsong-4 satellite are not available, though it can be assumed that its design is similar to KMS-3-2 which carried an Earth observation camera and a 470MHz UHF radio payload to transmit patriotic songs. KMS-3-2 was reported to be three-axis stabilized and its cameras were to be used to collect still imagery as well as videos, to be downlinked via X-Band. The satellite is expected to operate for at least four years and fulfill an Earth Observation task.");
        id.add("41332");

        names.add("METEOR M2");
        description.add("METEOR M2 was designed to watch global weather, the ozone layer, the ocean surface temperature and ice conditions to facilitate shipping in polar regions and to monitor radiation environment in the near-Earth space. The satellite was designed to operate in orbit for five years. It will become the second spacecraft in the Meteor-3M network, complementing the Meteor-M No. 1 satellite, which was launched on Sept. 17, 2009.");
        id.add("40069");

        names.add("ASIASAT 3S");
        description.add("28 C-band, 16 Ku-band; broadcasting, internet services, multimedia, telecommunications.AsiaSat 3S was launched for AsiaSat by a Proton-K / DM-2M launch vehicle on 21 March 1999, at 00:09:30 UTC, destined for an orbital location at 105.5° East.[2] A replacement for Asiasat 3, placed in the wrong orbit by a Proton launch in 1997, Asiasat 3S carried C-band and Ku-band transponders. The Blok DM-2M upper stage placed the satellite in a Geostationary transfer orbit (GTO). Asiasat's on-board R4D-11-300 apogee engine was then used to raise perigee to geostationary altitude.[3] It replaced AsiaSat 1 on 8 May 1999.");
        id.add("25657");

        names.add("NSS 12");
        description.add("NSS 12 was manufactured by Space Systems/Loral and has a 15-year design life the 12,400-pound (5,625-kg). The satellite carries 48 Ku-band and 40 C-band transponders. It will be parked in geostationary orbit over the equator at 57 degrees East longitude to take the place of the aging NSS 703 satellite, which was launched aboard an Atlas 2AS rocket from Cape Canaveral in 1994.");
        id.add("36032");

        names.add("AGILE");
        description.add("AGILE is an Italian gamma ray observatory launched aboard an Indian rocket today, beginning a three-year mission to survey the sky in a search for faraway sources of the Universe's most energetic form of light. The AGILE satellite flew into orbit on top of India's Polar Satellite Launch Vehicle. Liftoff was at 1000 GMT (6:00 a.m. EDT) from the Satish Dhawan Space Center at Sriharikota on India's eastern coast.");
        id.add("31135");

        names.add("MEASAT 3B");
        description.add("MEASAT 3B is a 13,000-pound (5,897-kilogram) geostationaysatellite taking a slot over the equator at 91.5 degrees east. Outfitted with 48 Ku-band transponders, Measat 3b will expand direct-to-home television services in Malaysia, Indonesia, India and Australia, serving more than 18 million households.");
        id.add("40147");

        names.add("ORBCOMM FM 106");
        description.add("Orbcomm is a family of low Earth orbit communications satellites, operated by the United States satellite communications company Orbcomm. As of July 2014, 51 such satellites have orbited Earth, with 50 still continuing to do so.");
        id.add("40088");

        names.add("MEASAT 3A");
        description.add("12 Ku-band and 12 C-band transponders to provide C-band communications services throughout Asia, the Middle East and Africa, and Ku-band direct-to-home television broadcasting to Malaysia and Indonesia.");
        id.add("35362");

        names.add("ORBCOMM FM114");
        description.add("ORBCOMM FM114 is one of the 11 Orbcomm commercial satellites launched on December 22, 2005. The satellites, made by Sierra Nevada Corp., will join 34 other satellites in Orbcomm's fleet to relay messages, track shipments and monitor maritime traffic for corporate clients.");
        id.add("41179");

        names.add("ORBCOMM FM119");
        description.add("ORBCOMM FM119 is one of the 11 Orbcomm commercial satellites launched on December 22, 2005. The satellites, made by Sierra Nevada Corp., will join 34 other satellites in Orbcomm's fleet to relay messages, track shipments and monitor maritime traffic for corporate clients.");
        id.add("41180");

        names.add("ORBCOMM FM105");
        description.add("ORBCOMM FM105 is one of the 11 Orbcomm commercial satellites launched on December 22, 2005. The satellites, made by Sierra Nevada Corp., will join 34 other satellites in Orbcomm's fleet to relay messages, track shipments and monitor maritime traffic for corporate clients.");
        id.add("41181");

        names.add("HST");
        description.add("The Hubble Space Telescope (HST) was the first and flagship mission of NASA's Great Observatories program. Designed to complement the wavelength capabilities of the other spacecraft in the program (CGRO, AXAF, and SIRTF), HST was a 2.4 m, f/24 Ritchey-Chretien telescope capable of performing observations in the visible, near-ultraviolet, and near-infrared (1150 A to 1 mm).");
        id.add("20580");

        names.add("ORBCOMM FM110");
        description.add("ORBCOMM FM110 is one of the 11 Orbcomm commercial satellites launched on December 22, 2005. The satellites, made by Sierra Nevada Corp., will join 34 other satellites in Orbcomm's fleet to relay messages, track shipments and monitor maritime traffic for corporate clients.");
        id.add("41182");

        names.add("GSAT 19");
        description.add("GSAT-19 is an Indian communications satellite launched by the ISRO. The satellite will act as a testbed for the modular I-6K satellite bus, carrying experimental technologies such as ion thrusters for manoeuvring and stabilisation, active thermal control using thermal radiators, a miniaturised inertial reference unit, indigenously produced lithium-ion batteries, and C-band traveling-wave-tube amplifiers.");
        id.add("42747");

        names.add("NAVSTAR 75 (USA 265)");
        description.add("NAVSTAR 75 (USA 265), also known as GPS IIF-11 is a navigation satellite deployed in Plane E of the GPS constellation.");
        id.add("41019");

        names.add("KMS 3-2");
        description.add("KWANGMYONGSONG 3 is a North Korean Earth observation satellite, which according to the DPRK is designed for weather forecast purposes, and whose launch is widely portrayed in the West to be a veiled ballistic missile test.");
        id.add("39026");

        names.add("FUNCUBE 1 (AO-73)");
        description.add("FUNcube-1 is a complete educational single CubeSat project with the goal of enthusing and educating young people about radio, space, physics and electronics.");
        id.add("39444");

        names.add("ALPHASAT (INMARSAT 4A-F4)");
        description.add("ALPHASAT (INMARSAT 4A-F4)");
        id.add("39215");

        names.add("QZS-1 (MICHIBIKI)");
        description.add("QZS-1 (MICHIBIKI) is the first of three planned satellites to fill coverage gaps from U.S. Global Positioning System satellites caused by signal blockage from mountains and skyscrapers. Developed by the Japan Aerospace Exploration Agency (JAXA) and four government ministries, the Quasi-Zenith Satellite System is aimed at overcoming ground interference by launching more navigation satellites strategically positioned high in the sky above Asia. The line-of-sight navigation signals from GPS satellites can be interrupted in rural hamlets and skyscraper-filled cities like Tokyo, where users occasionally lack sufficient data to determine their position, elevation and time. JAXA says a constellation of three QZSS spacecraft would make three-dimensional navigation coverage available 99.8 percent of the time, an improvement over the estimated 90 percent availability with GPS satellites today. MICHIBIKI will circle the Earth at a 45-degree angle to the equator. Its orbital high point will be more than 24,000 miles (38,600 km) over the northern hemisphere and its lowest altitude will be 20,500 miles (33,000 km), according to JAXA. Projected against Earth's surface, MICHIBIKI's ground track will chart an asymmetric figure-eight pattern stretching from Japan to Australia as it alternates north and south of the equator.");
        id.add("37158");

        names.add("VIASAT 2");
        description.add("VIASAT 2 is a commercial communications satellite which will be the world's highest capacity communications satellite after it becomes commercially available in early 2018.");
        id.add("42740");

        names.add("LANDSAT 8");
        description.add("The Landsat Data Continuity Mission (LDCM), a collaboration between NASA and the U.S. Geological Survey, will provide moderate-resolution (15 m -100 m, depending on spectral frequency) measurements of the Earth's terrestrial and polar regions in the visible, near-infrared, short wave infrared, and thermal infrared. LDCM will provide continuity with the 40-year long Landsat land imaging data set. In addition to widespread routine use for land use planning and monitoring on regional to local scales, support of disaster response and evaluations, and water use monitoring, LDCM measurements directly serve NASA research in the focus areas of climate, carbon cycle, ecosystems, water cycle, biogeochemistry, and Earth surface/interior.");
        id.add("39084");


        for(int i=0;i<names.size();i++){
            Satellite satellite=new Satellite();
            satellite.setName(names.get(i));
            satellite.setDescription(description.get(i));
            satellite.setId(id.get(i));
            satellites.add(satellite);
        }
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        SatelliteAdapter adapter= new SatelliteAdapter(this,satellites);
        recycleView.setAdapter(adapter);

    }

    public void createSatelliteObjectList(){

    }
}