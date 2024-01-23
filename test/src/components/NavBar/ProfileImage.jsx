import profile from '../../assets/images/NCT_도영.PNG';

import * as React from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import ChatOutlinedIcon from '@mui/icons-material/ChatOutlined';
import PersonOutlinedIcon from '@mui/icons-material/PersonOutlined';
import Divider from '@mui/material/Divider';

import theme from '../../styles/theme'; 

import GPS from './GPS';

import { useNavigate } from 'react-router-dom';
import { Paper } from '@mui/material';

const ProfileImage = () => {
    
    const [anchorElUser, setAnchorElUser] = React.useState(null);
    
    const navigate = useNavigate();
  
    const handleOpenUserMenu = (event) => {
        setAnchorElUser(event.currentTarget);
    };
  
    const handleClickUserMenu = async (to) => {
        setAnchorElUser(null);
        await navigate(to);
    };

    const handleCloseUserMenu = () => {
        setAnchorElUser(null);
    };


    return (
        <div class="profile-image-container">
            
            <Box id='menu-container'>
                <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
                    <img id='profile-image' className='profile-image gradient-border background-image' src={profile}></img>
                </IconButton>

                <Menu
                className='profile-container'
                sx={{ mt: '4rem' }}
                id="menu-appbar"
                anchorEl={anchorElUser}
                anchorOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                }}
                keepMounted
                transformOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                }}
                open={Boolean(anchorElUser)}
                onClose={handleCloseUserMenu}

                theme={theme}
                >
                <GPS/>
                <Divider variant="middle" component="li" />
                <MenuItem onClick={async () => { await handleClickUserMenu('/chat'); }}>
                    <ChatOutlinedIcon className='dropdown-icon'/>
                    <Typography textAlign="center">나의 채팅함</Typography>
                </MenuItem>
                <MenuItem onClick={async () => { await handleClickUserMenu('/profile'); }}>
                    <PersonOutlinedIcon className='dropdown-icon'/>
                    <Typography textAlign="center">마이페이지</Typography>
                </MenuItem>
                <Button variant="contained" color="primary" disableElevation>
                    로그아웃
                </Button>
                
                
                </Menu>
            </Box>
        </div>
        
    )
}

export default ProfileImage;