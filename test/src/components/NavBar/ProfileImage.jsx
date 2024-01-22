import profile from '../../assets/images/NCT_도영.PNG';

const ProfileImage = () => {
    return (
        <div class="profile-image-container">
            <img id='profile-image' class='profile-image gradient-border background-image' src={profile}></img>
        </div>
    )
}

export default ProfileImage;